package com.example.prospecta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProspectaInterviewQuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProspectaInterviewQuestionApplication.class, args);
    }

    @Bean
    public RestTemplate getTemplate() {
    	return new RestTemplate();
    }

}
