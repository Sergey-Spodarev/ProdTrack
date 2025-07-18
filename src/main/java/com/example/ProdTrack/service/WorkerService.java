package com.example.ProdTrack.service;

import com.example.ProdTrack.DTO.TaskDTO;
import com.example.ProdTrack.model.Task;
import com.example.ProdTrack.model.Users;
import com.example.ProdTrack.model.enums;
import com.example.ProdTrack.repository.UserRepository;
import com.example.ProdTrack.repository.TaskRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkerService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public WorkerService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAllTasksUser(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getAssignedTasks().stream()
                .map(this::convertTaskToDTO)
                .toList();
    }

    public TaskDTO updateTask(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Task not found"));
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setTitle(taskDTO.getTitle());
        if (updateRequired(taskDTO.getStage(), taskDTO.getStatus())) {
            task.setComment("");
            updateTaskStage(task, taskDTO.getStage());
            task.setStatus(enums.Status.WAITING);
        } else {
            task.setComment(taskDTO.getComment());
            task.setStage(taskDTO.getStage());
            task.setStatus(taskDTO.getStatus());
        }
        return convertTaskToDTO(taskRepository.save(task));
    }

    public void updateTaskStage(Task task, enums.Stage stage) {
        switch (stage) {
            case PREPARATION:
                task.setStage(enums.Stage.ASSEMBLING);
                break;
            case ASSEMBLING:
                task.setStage(enums.Stage.CONTROL);
                break;
            case CONTROL:
                task.setStage(enums.Stage.PACKAGING);
                break;
            case PACKAGING:
                task.setStage(enums.Stage.COMPLETED);
                break;
            default:
                throw new IllegalStateException("Unknown stage: " + stage);
        }
    }

    public boolean updateRequired(enums.Stage stage, enums.Status status) {
        return status == enums.Status.COMPLETED && stage != enums.Stage.PACKAGING;
    }

    public TaskDTO convertTaskToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setStage(task.getStage());
        taskDTO.setComment(task.getComment());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setAssigneeUsername(task.getAssigned().getUsername());
        return taskDTO;
    }
}
