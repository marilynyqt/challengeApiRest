package com.alura.callengeApiRest.controller;

import com.alura.callengeApiRest.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        // 🚨 Aquí se debería validar contra la BD, por ahora lo hacemos simple
        if ("admin".equals(username) && "1234".equals(password)) {
            String token = jwtService.generateToken(username);
            Map<String, String> response = new HashMap<>();
            response.put("type", "Bearer");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
