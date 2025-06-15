package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.management.model.request.CommentRequest;
import org.example.management.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Добавление комментария")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Комментарий успешно добавлен"),
            @ApiResponse(responseCode = "400", description = "Неверный формат данных"),
            @ApiResponse(responseCode = "401", description = "Не авторизован")
    })
    @PostMapping
    public void addComment(@RequestBody CommentRequest commentRequest) {
        commentService.addComment(commentRequest);
    }
}
