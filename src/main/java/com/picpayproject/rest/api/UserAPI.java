package com.picpayproject.rest.api;

import com.picpayproject.dtos.UserDTO;
import com.picpayproject.repository.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserAPI {
    @PostMapping("/create")
    ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO);

    @GetMapping("/all")
    ResponseEntity<List<User>> getAllUsers();
}
