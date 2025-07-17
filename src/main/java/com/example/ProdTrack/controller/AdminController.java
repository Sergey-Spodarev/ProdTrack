package com.example.ProdTrack.controller;

import com.example.ProdTrack.DTO.TaskDTO;
import com.example.ProdTrack.DTO.UserDTO;
import com.example.ProdTrack.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/getAllTask")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(adminService.getAllTasks());
    }

    @GetMapping("/getAllUsersWithTask")
    public ResponseEntity<List<UserDTO>> getAllUsersWithTask() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminService.getAllUsersWithTask());
    }

    @PostMapping("/addTask")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminService.addTask(taskDTO, username));
    }

    @PutMapping("/updateTask")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminService.updateTask(taskDTO));
    }

    @DeleteMapping("/delTask/{id}")//todo проверить на сайте работаспасобнасть
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        adminService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
