package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.model.UserList;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Context;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody @Valid User user) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(user));
    }

    @GetMapping("/users")
    public UserList getAllUsers(){
        return new UserList(service.getAllUsers());
    }

    @GetMapping("/users/{email}")
    public User getUserByEmail(@PathVariable("email") String email) throws Exception {

        Optional<User> optionalUser = service.getUserByEmail(email);

        if(optionalUser.isPresent()){
            return optionalUser.get();
        }

        throw  new Exception("Brak użytkownika z emailem " + email);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable("id") Long id){

        service.deleteUser(id);

    }

    @PutMapping("/user/{id}")
    public void updateUserById(@PathVariable("id") Long id, @RequestBody @Valid User user){
        service.updateUser(id, user);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) throws Exception {

        Optional<User> optionalUser = service.getUserById(id);

        if(optionalUser.isPresent()){
            return optionalUser.get();
        }

        throw  new Exception("Brak użytkownika z id " + id);
    }

    @GetMapping("/email/{id}")
    public void sendEmail (@PathVariable("id") Long id) throws Exception {

        try {
            Optional<User> optionalUser = service.getUserById(id);

            if (optionalUser.isPresent()) {
                service.sendEmail(id);
            }
        } catch(Exception e) {
            throw new Exception("Brak użytkownika z id " + id);
        }
    }



}
