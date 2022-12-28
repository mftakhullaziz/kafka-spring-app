package com.service.KafkaSpringProducer.service;

import com.service.KafkaSpringProducer.payload.UserRequest;
import com.service.KafkaSpringProducer.persistence.UserEntity;

import java.util.List;

public interface UserGateway {
    UserEntity createUser(UserRequest request);

    List<UserEntity> getUser();
}
