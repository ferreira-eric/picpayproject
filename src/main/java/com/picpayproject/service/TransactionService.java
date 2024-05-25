package com.picpayproject.service;

import com.picpayproject.dtos.AuthorizationDTO;
import com.picpayproject.dtos.TransactionDTO;
import com.picpayproject.exception.UnauthorizedTransactionException;
import com.picpayproject.repository.TransactionRepository;
import com.picpayproject.repository.entity.Transaction;
import com.picpayproject.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    @Value("${authorization.api.url}")
    String AuthorizationApiUrl;

    public Transaction createTransaction(TransactionDTO dto) throws Exception {
        User sender = userService.findUserById(dto.getSenderId());
        User receiver = userService.findUserById(dto.getReceiverId());

        userService.validateTransaction(sender, dto.getValue());

        if(!authorizeTransaction()) throw new UnauthorizedTransactionException();

        Transaction transaction = new Transaction();
        populateTransaction(transaction, sender, receiver, dto.getValue());
        balanceTransaction(sender, receiver, dto);

        transaction = transactionRepository.save(transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        sendNotificationToSenderAndReceiver(sender, receiver);

        return transaction;
    }

    private boolean authorizeTransaction() {
        ResponseEntity<AuthorizationDTO> response = restTemplate.getForEntity(AuthorizationApiUrl, AuthorizationDTO.class);

        if(response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            AuthorizationDTO authorizationDTO = response.getBody();

            return authorizationDTO.getData().isAuthorization();
        }

        return false;
    }

    private void sendNotificationToSenderAndReceiver(User sender, User receiver) throws Exception {
        notificationService.sendNotification(sender, "Transaction completed successfully");
        notificationService.sendNotification(receiver, "Transaction received successfully");
    }

    private void balanceTransaction(User sender, User receiver, TransactionDTO dto) {
        sender.setBalance(sender.getBalance().subtract(dto.getValue()));
        receiver.setBalance(receiver.getBalance().add(dto.getValue()));
    }

    private void populateTransaction(Transaction transaction, User sender, User receiver, BigDecimal value) {
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(value);
    }
}
