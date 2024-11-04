package com.cuentacorrienteapp.cuentacorrienteapp.mappers;

import org.mapstruct.Mapper;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestRegisterDtoToUser(RequestRegisterDto requestRegisterDto);

    User requestLoginDtoTouser(RequestLoginDto requestLoginDto);
    
    ResponseLoginDto userToResponseUserDto(User user);

    ResponseRegisterDto userToResponseRegisterDto(User user);

    ResponseUserNotRegisteredDto userToResponseUserNotRegisteredDto(User user);

} 