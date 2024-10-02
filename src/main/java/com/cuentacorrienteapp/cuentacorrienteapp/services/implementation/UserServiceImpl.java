// package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;
// import com.cuentacorrienteapp.cuentacorrienteapp.repositories.UserRepository;
// import com.cuentacorrienteapp.cuentacorrienteapp.services.UserService;

// import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
// @Service
// public class UserServiceImpl implements UserService{

//     private final UserRepository userRepository;

//     @Override
//     @Transactional(readOnly = true)
//     public List<User> findAll() {
//         return (List<User>) userRepository.findAll();
//     }

//     @Override
//     @Transactional(readOnly = true)
//     public Optional<User> findById(Long id) {
//         return userRepository.findById(id);
//     }

//     @Override
//     @Transactional
//     public User save(User user) {
//         return userRepository.save(user);
//     }

//     @Override
//     @Transactional
//     public Optional<User> update(Long id, User user) {
//         Optional<User> optionalUser = userRepository.findById(id);

//         if (optionalUser.isPresent()){
//             User usr = optionalUser.orElseThrow();
            
//             usr.setEmail(user.getEmail());
//             usr.setId(user.getId());
//             usr.setName(user.getName());

//             return Optional.of(userRepository.save(usr));
//         }
//         return optionalUser;
//     }

// }
