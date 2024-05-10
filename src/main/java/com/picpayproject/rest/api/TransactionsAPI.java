package com.picpayproject.rest.api;

import com.picpayproject.dtos.TransactionsDTO;
import com.picpayproject.repository.entity.Transactions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionsAPI {

    @PostMapping("/create")
    ResponseEntity<Transactions> createTransactions(@RequestBody TransactionsDTO dto) throws Exception;
}
