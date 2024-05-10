package com.picpayproject.service;

import com.picpayproject.dtos.NotificationDTO;
import com.picpayproject.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String mensage) throws Exception {
        String email = user.getEmail();

        NotificationDTO notificationRequest = new NotificationDTO(email, mensage);

        //TODO criar variavel de ambiente
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class);

        if (!notificationResponse.getStatusCode().equals(HttpStatus.OK)) {
            //TODO criar exception especifica
            throw new Exception("Serviço de email fora do ar");
        }
    }
}
