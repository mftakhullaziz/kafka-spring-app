package com.service.KafkaSpringProducer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.KafkaSpringProducer.payload.User;
import com.service.KafkaSpringProducer.payload.UserRequest;
import com.service.KafkaSpringProducer.persistence.UserEntity;
import com.service.KafkaSpringProducer.repository.UserRepository;
import com.service.KafkaSpringProducer.service.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class UserService implements UserGateway {

    @Value("${user.topic.name}")
    private String topicName;

    private final UserRepository repository;
    private final KafkaTemplate<String, String> kafkaProducer;
    private final DateTimeFormatter patternDateTime = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final LocalDateTime dateTime = LocalDateTime.now();

    public UserService(UserRepository repository, KafkaTemplate<String, String> kafkaProducer) {
        this.repository = repository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public UserEntity createUser(UserRequest request) {
        UserEntity user = repository.save(constructBuild(request));
        String messageToConsumer = null;
        try {
            User userData = new User();
            userData.setUserId(user.getUserId());
            userData.setName(user.getName());
            userData.setUsername(user.getUsername());
            userData.setEmail(user.getEmail());
            userData.setPhone(user.getPhone());
            userData.setDateIndex(user.getDateIndex());
            messageToConsumer = new ObjectMapper().writeValueAsString(userData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaProducer.send(new ProducerRecord<>(topicName, messageToConsumer));
        return user;
    }

    private UserEntity constructBuild(UserRequest request) {
        return UserEntity.builder().email(request.getEmail()).name(request.getName()).
               username(request.getUsername()).phone(request.getPhone()).
               dateIndex(Integer.valueOf(patternDateTime.format(dateTime))).build();
    }

    @Override
    public List<UserEntity> getUser() {
        List<UserEntity> user = repository.findAll();
        String messageToConsumer = null;
        try {
            messageToConsumer = new ObjectMapper().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaProducer.send(new ProducerRecord<>(topicName, messageToConsumer));
        return user;
    }
}
