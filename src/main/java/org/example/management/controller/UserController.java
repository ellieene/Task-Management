package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.management.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AdminService adminService;

    @PutMapping("/change-user-to-admin")
    @Operation(summary = "Изменение роли на ROLE_ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль успешно изменена"),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    public ResponseEntity<Void> changeUserToAdmin() {
        adminService.changeUserToAdmin();
        return ResponseEntity.ok().build();
    }
}
