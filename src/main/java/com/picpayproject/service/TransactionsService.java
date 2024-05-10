package com.picpayproject.service;

import com.picpayproject.dtos.TransactionsDTO;
import com.picpayproject.repository.TransactionsRepository;
import com.picpayproject.repository.entity.Transactions;
import com.picpayproject.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transactions createTransaction(TransactionsDTO dto) throws Exception {
        //TODO refatorar metodo
        User sender = userService.findUserById(dto.getSenderId());
        User receiver = userService.findUserById(dto.getReceiverId());

        userService.validateTransaction(sender, dto.getValue());

        boolean isAuthorized = authorizeTransaction();

        //TODO criar exception especifica
        if(!isAuthorized) throw new Exception("Transação não autorizada");

        Transactions transactions = new Transactions();
        transactions.setSender(sender);
        transactions.setReceiver(receiver);
        transactions.setAmount(dto.getValue());

        sender.setBalance(sender.getBalance().subtract(dto.getValue()));
        receiver.setBalance(receiver.getBalance().add(dto.getValue()));

        transactions = transactionsRepository.save(transactions);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        notificationService.sendNotification(sender, "Transacao efetuada com sucesso");
        notificationService.sendNotification(receiver, "Transação recebida com sucesso");
        return transactions;
    }

    public boolean authorizeTransaction() {
         ResponseEntity<Map> response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        return response.getStatusCode().equals(HttpStatus.OK);
    }
}