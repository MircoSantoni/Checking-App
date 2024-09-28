package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
}