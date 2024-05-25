package com.picpayproject.rest.controllers;

import com.picpayproject.dtos.TransactionDTO;
import com.picpayproject.repository.entity.Transaction;
import com.picpayproject.rest.api.TransactionAPI;
import com.picpayproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController implements TransactionAPI {

    @Autowired
    private TransactionService service;

    @Override
    public ResponseEntity<Object> createTransactions(TransactionDTO dto) {
        try {
            Transaction transaction = service.createTransaction(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
