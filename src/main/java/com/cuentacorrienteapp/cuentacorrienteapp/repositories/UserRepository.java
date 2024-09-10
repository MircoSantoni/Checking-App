package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
}