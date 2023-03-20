package com.example.demo_jwt_1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuthentication")
public class TestController {

    @GetMapping("/user")
    public String userEndPoint() {
        return "Hello user !";
    }

    @GetMapping("/admin")
    public String adminEndPoint() {
        return "Hello admin !";
    }
}
