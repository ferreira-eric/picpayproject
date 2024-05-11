package com.picpayproject.rest.api;

import com.picpayproject.dtos.TransactionDTO;
import com.picpayproject.repository.entity.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionAPI {

    @PostMapping("/create")
    ResponseEntity<Transaction> createTransactions(@RequestBody TransactionDTO dto) throws Exception;
}
