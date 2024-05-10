package com.picpayproject.rest.controllers;

import com.picpayproject.dtos.UserDTO;
import com.picpayproject.repository.entity.User;
import com.picpayproject.rest.api.UserAPI;
import com.picpayproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements UserAPI {

    @Autowired
    private UserService service;

    @Override
    public ResponseEntity<User> createUser(UserDTO userDTO) throws Exception {
        //TODO tratar exception
        User user = service.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }
}
