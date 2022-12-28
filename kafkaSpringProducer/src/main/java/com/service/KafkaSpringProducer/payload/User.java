package com.service.KafkaSpringProducer.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Integer userId;
    private String email;
    private String username;
    private String name;
    private String phone;
    private Integer dateIndex;
}
