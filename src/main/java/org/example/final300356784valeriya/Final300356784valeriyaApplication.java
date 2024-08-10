package org.example.final300356784valeriya;

import org.example.final300356784valeriya.repositories.SalesmanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Final300356784valeriyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Final300356784valeriyaApplication.class, args);
    }



    @Bean
    CommandLineRunner commandLineRunner(SalesmanRepository salesmanRepository){
        return args -> {
        }

                ;}
}
