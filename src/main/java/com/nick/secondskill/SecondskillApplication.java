package com.nick.secondskill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.nick.secondskill.dao")
public class SecondskillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondskillApplication.class, args);
    }

}
