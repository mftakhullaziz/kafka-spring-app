package com.service.KafkaSpringProducer.repository;

import com.service.KafkaSpringProducer.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
