package com.example.bootboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing      //AuditingEntityListener 활성화를 위해 추가
public class BootBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootBoardApplication.class, args);
    }

}
