package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

<<<<<<< Updated upstream
import org.springframework.data.repository.CrudRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
        Optional<User> findByEmail(String email);
>>>>>>> Stashed changes
}