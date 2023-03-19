package com.example.demo_jwt_1.controller;

import com.example.demo_jwt_1.jwt.JwtTokenUtil;
import com.example.demo_jwt_1.model.dto.UserDTO;
import com.example.demo_jwt_1.model.request.UserRequest;
import com.example.demo_jwt_1.model.rsponse.Response;
import com.example.demo_jwt_1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;


    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    private ResponseEntity<Response<UserDTO>> login(
            @RequestBody UserRequest userRequest
    ) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userRequest.getEmail(), userRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = userService
                .loadUserByUsername(userRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println(token);
//        Response<UserDTO> response = Response.<UserDTO>builder()
//                .message("Login successful !")
//                .payload()
        return ResponseEntity.ok().body(null);
    }

}
