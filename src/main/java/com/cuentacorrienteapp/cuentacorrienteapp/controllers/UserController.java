package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cuentacorrienteapp.cuentacorrienteapp.services.UserService;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

@Autowired
private UserService serviceUser;



@GetMapping("/{id}")
public ResponseEntity<?> findById(@PathVariable Long id) {
    Optional<User> optionalUser = serviceUser.findById(id);

    if (optionalUser.isPresent()){
        return ResponseEntity.ok(optionalUser.orElseThrow());
    }
    return ResponseEntity.notFound().build();
}

@PostMapping()
public  ResponseEntity<?> create(@Valid @RequestBody User user) {
    return  ResponseEntity.status(HttpStatus.CREATED).body(serviceUser.save(user));
}

@PutMapping("/{id}")
public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        Optional<User> optionalUser = serviceUser.update(id,user);
     
        if (optionalUser.isPresent()){
           return ResponseEntity.ok(optionalUser.orElseThrow());
        }
        return ResponseEntity.notFound().build();
 }

@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<User> optionalUser = serviceUser.findById(id);

    if (optionalUser.isPresent()){
        return ResponseEntity.ok(optionalUser.orElseThrow());
    }
    return ResponseEntity.notFound().build();
}
}

