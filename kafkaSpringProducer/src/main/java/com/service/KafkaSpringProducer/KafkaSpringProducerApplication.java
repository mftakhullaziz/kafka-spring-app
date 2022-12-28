package com.service.KafkaSpringProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class KafkaSpringProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpringProducerApplication.class, args);
	}

}
