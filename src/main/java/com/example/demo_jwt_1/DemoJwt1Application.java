package com.example.demo_jwt_1;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(
        name = "bearerAuthentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class DemoJwt1Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoJwt1Application.class, args);
    }

}
