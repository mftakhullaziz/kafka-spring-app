package com.service.KafkaSpringProducer.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserResponse {
    private String email;
    private String username;
    private String name;
    private String phone;
}
