package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.management.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Аутентификация")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Регистрация")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "409", description = "Email уже занят"),
            @ApiResponse(responseCode = "400", description = "Неверный формат данных")
    })
    @PostMapping("/registration")
    public ResponseEntity<UUID> register(@RequestHeader(value = "token") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(token));
    }

    @Operation(summary = "Авторизация")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешная авторизация"),
            @ApiResponse(responseCode = "401", description = "Неверные учетные данные"),
            @ApiResponse(responseCode = "400", description = "Неверный формат токена")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader(value = "token") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.authorization(token));
    }
}
