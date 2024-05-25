package com.picpayproject.rest.api;

import com.picpayproject.dtos.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionAPI {

    @PostMapping("/create")
    ResponseEntity<Object> createTransactions(@RequestBody TransactionDTO dto);
}
