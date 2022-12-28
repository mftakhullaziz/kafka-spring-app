package com.service.kafkaSpringConsumer.repostiory;

import com.service.kafkaSpringConsumer.persistence.UserConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConsumerRepository extends JpaRepository<UserConsumerEntity, Integer> {
}
