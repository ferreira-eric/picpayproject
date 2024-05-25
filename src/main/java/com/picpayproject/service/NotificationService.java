package com.picpayproject.service;

import com.picpayproject.dtos.AuthorizationDTO;
import com.picpayproject.dtos.NotificationDTO;
import com.picpayproject.exception.EmailServiceIsFailException;
import com.picpayproject.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${notification.api.url}")
    private String NotificationApiUrl;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();

        NotificationDTO notificationRequest = new NotificationDTO();
        notificationRequest.setEmail(email);
        notificationRequest.setMessage(message);

        ResponseEntity<AuthorizationDTO> notificationResponse = restTemplate.getForEntity(NotificationApiUrl, AuthorizationDTO.class);

        if (!notificationResponse.getStatusCode().equals(HttpStatus.OK) || notificationResponse.getBody() == null) {
            throw new EmailServiceIsFailException();
        }
    }
}
