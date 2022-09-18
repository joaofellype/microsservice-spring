package com.microsservice.bdcentral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class BdcentralApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdcentralApplication.class, args);
	}

}
