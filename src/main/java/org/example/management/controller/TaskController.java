package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.management.model.dto.TaskDto;
import org.example.management.model.enums.TaskPriority;
import org.example.management.model.enums.TaskStatus;
import org.example.management.model.request.*;
import org.example.management.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Создание задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Задача успешно создана"),
            @ApiResponse(responseCode = "400", description = "Неверный формат данных"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    public ResponseEntity<UUID> create(@RequestBody TaskCreateRequest taskCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskCreateRequest));
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Изменение задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно изменена"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    public ResponseEntity<Void> editTask(@PathVariable UUID taskId, @RequestBody TaskEditRequest taskEditRequest) {
        taskService.editTask(taskId, taskEditRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Удаление задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Задача успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{taskId}/priority")
    @Operation(summary = "Изменение приоритета задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Приоритет успешно изменен"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    public ResponseEntity<String> editPriority(@PathVariable UUID taskId, @RequestParam TaskPriority priority) {
        taskService.editPriority(taskId, priority);
        return ResponseEntity.ok().body("Приоритет задачи успешно изменен");
    }

    @PutMapping("/{taskId}/status")
    @Operation(summary = "Изменение статуса задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Статус успешно изменен"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    public ResponseEntity<Void> changeStatusTask(@PathVariable UUID taskId, @RequestParam TaskStatus status) {
        taskService.changeStatus(taskId, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{taskId}/executor/{executorId}")
    @Operation(summary = "Назначение исполняемого задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Исполнитель успешно назначен"),
            @ApiResponse(responseCode = "404", description = "Задача или пользователь не найдены"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    public ResponseEntity<String> changeExecutor(@PathVariable UUID taskId, @PathVariable UUID executorId) {
        taskService.changeExecutor(taskId, executorId);
        return ResponseEntity.ok().body("Исполняемый назначен");
    }

    @PostMapping("/find")
    @Operation(summary = "Просмотреть все задачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список задач успешно получен"),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "400", description = "Неверные параметры пагинации")
    })
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size,
                                                     @RequestBody(required = false) GetFilterTask getFilterTask) {
        return ResponseEntity.ok().body(taskService.findTasks(getFilterTask, page, size));
    }
}
