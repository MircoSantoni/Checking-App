package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.RequestLoginDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.RequestRegisterDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.ResponseLoginDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.ResponseRegisterDto;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;
import com.cuentacorrienteapp.cuentacorrienteapp.exceptions.InvalidUserCredentialsException;
import com.cuentacorrienteapp.cuentacorrienteapp.exceptions.ResourceAlreadyExistsException;
import com.cuentacorrienteapp.cuentacorrienteapp.exceptions.ResourceNotFoundException;
import com.cuentacorrienteapp.cuentacorrienteapp.mappers.UserMapper;
import com.cuentacorrienteapp.cuentacorrienteapp.services.AuthenticationService;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.UserRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.security.JwtService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseRegisterDto signUp(@Valid RequestRegisterDto requestRegisterDto) {
        if (userRepository.findByEmail(requestRegisterDto.email()).isPresent()) {
            throw new ResourceAlreadyExistsException("Ya hay una cuenta asociada con el email " + requestRegisterDto.email() + ".");
        }
        if (userRepository.findByCuit(requestRegisterDto.cuit()).isPresent()) {
            throw new ResourceAlreadyExistsException("Ya hay una cuenta asociada con el cuit " + requestRegisterDto.cuit() + ".");
        }

        User user = userMapper.requestRegisterDtoToUser(requestRegisterDto);
        user.setPassword(passwordEncoder.encode(requestRegisterDto.password()));
        user.setCuit(requestRegisterDto.cuit());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        User savedUser = userRepository.save(user);

        return userMapper.userToResponseRegisterDto(savedUser);
    }

    @Override
    @Transactional
    public ResponseLoginDto login(RequestLoginDto requestLoginDto) {
        User user = userRepository.findByEmail(requestLoginDto.email()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado."));
        
        userRepository.findByCuit(requestLoginDto.cuit()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!user.getCuit().equals(requestLoginDto.cuit())) {
            throw new InvalidUserCredentialsException("El CUIT no corresponde al usuario");
        }
        if (!user.isEnabled()) {
            throw new InvalidUserCredentialsException("Cuenta no verificada. Por favor verifique su cuenta.");
        }
        
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLoginDto.email(),
                            requestLoginDto.password()                    )
            );
        } catch (BadCredentialsException ex) {
            throw new InvalidUserCredentialsException("Email, contraseña o cuit inválidos.");
        }

        String jwtToken = jwtService.generateToken(user);

        return new ResponseLoginDto(user.getId(), jwtToken, jwtService.getExpirationTime(), user.getName(), user.getSurname(), user.getEmail());
    }

}
