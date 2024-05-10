package com.picpayproject.service;

import com.picpayproject.dtos.UserDTO;
import com.picpayproject.enums.UserType;
import com.picpayproject.repository.UserRepository;
import com.picpayproject.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType().equals(UserType.MERCHANT)) {
            //TODO criar exception especifica
            throw new Exception("Usuário do tipo logista não está autorizado a fazer transação");
        }

        if(sender.getBalance().compareTo(amount) < 0) {
            //TODO criar exception especifica
            throw new Exception("Usuário com saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        //TODO criar exception especifica
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public Optional<User> findOptionalUserById(Long id){
        return userRepository.findUserById(id);
    }

    public User createUser(UserDTO dto) throws Exception {
        var findUser = userRepository.findUserByDocument(dto.getDocument());

        if(findUser.isPresent()) {
            //TODO criar exception especifica
           throw new Exception("Usuario já existente");
        }

        User user = User.deserialize(dto);

        user = userRepository.save(user);
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
         return userRepository.findAll();
    }
}
