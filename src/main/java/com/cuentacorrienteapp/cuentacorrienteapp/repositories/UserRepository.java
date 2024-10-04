package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
        Optional<User> findByEmail(String email);
}