package com.service.kafkaSpringConsumer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.kafkaSpringConsumer.persistence.UserConsumerEntity;
import com.service.kafkaSpringConsumer.repostiory.UserConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    @Value("${user.topic.name}")
    private String topicName;

    private final UserConsumerRepository repository;

    public UserConsumer(UserConsumerRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "user-topic", groupId = "users")
    public void listenCustomerFoo(String message) {
        System.out.println("Received Message in group users: " + message);
//        ObjectMapper object = new ObjectMapper();
//        UserConsumerEntity user = new UserConsumerEntity();
//        try {
//            user = object.readValue(message, UserConsumerEntity.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}
