package com.example.ProdTrack.controller;

import com.example.ProdTrack.DTO.TaskDTO;
import com.example.ProdTrack.service.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/worker")
public class WorkerController {
    private final WorkerService workerService;
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/getTask")
    public ResponseEntity<List<TaskDTO>> getAllTasksUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workerService.getAllTasksUser(username));
    }
}
