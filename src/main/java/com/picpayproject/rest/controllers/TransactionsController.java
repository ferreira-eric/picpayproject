package com.picpayproject.rest.controllers;

import com.picpayproject.dtos.TransactionsDTO;
import com.picpayproject.repository.entity.Transactions;
import com.picpayproject.rest.api.TransactionsAPI;
import com.picpayproject.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionsController implements TransactionsAPI {

    @Autowired
    private TransactionsService service;

    @Override
    public ResponseEntity<Transactions> createTransactions(TransactionsDTO dto) throws Exception {
        //TODO tratar exception
        Transactions transactions = service.createTransaction(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactions);
    }
}
