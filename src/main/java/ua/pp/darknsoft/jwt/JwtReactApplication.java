package ua.pp.darknsoft.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class JwtReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtReactApplication.class, args);
    }


}
