package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.config.JwtUtil;
import com.unisporti.api_unisporti.model.User;
import com.unisporti.api_unisporti.service.UserService;
import com.unisporti.api_unisporti.vo.LoginRequestVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestVO loginRequest) {
        User user = userService.authenticate(loginRequest.getCpf(), loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }

        final String token = jwtUtil.generateToken(user.getCpf(), user.getIdUser(), user.getPassword(), user.getRole());

        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
