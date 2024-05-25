package com.picpayproject.service;

import com.picpayproject.dtos.UserDTO;
import com.picpayproject.enums.UserType;
import com.picpayproject.exception.UnauthorizedTransactionException;
import com.picpayproject.exception.UserAlreadyExistException;
import com.picpayproject.exception.UserWithInsufficientBalanceException;
import com.picpayproject.repository.UserRepository;
import com.picpayproject.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType().equals(UserType.MERCHANT)) {
            throw new UnauthorizedTransactionException();
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            throw new UserWithInsufficientBalanceException();
        }
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new Exception(String.format("User not Found by id{%d}", id)));
    }

    public User createUser(UserDTO userDTO) throws Exception {
        validateUser(userDTO);

        User user = User.deserialize(userDTO);
        user = userRepository.save(user);

        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
         return userRepository.findAll();
    }

    public void validateUser (UserDTO userDTO) throws Exception{
        var findUser = userRepository.findUserByDocumentAndEmail(userDTO.getDocument(), userDTO.getEmail());

        if(findUser.isPresent()) {
            throw new UserAlreadyExistException();
        }
    }
}
