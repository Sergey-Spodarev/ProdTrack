package com.example.ProdTrack.service;

import com.example.ProdTrack.DTO.TaskDTO;
import com.example.ProdTrack.model.Task;
import com.example.ProdTrack.model.Users;
import com.example.ProdTrack.model.enums;
import com.example.ProdTrack.repository.UserRepository;
import com.example.ProdTrack.repository.WorkerRopository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkerService {
    private final UserRepository userRepository;
    private final WorkerRopository workerRopository;

    public WorkerService(UserRepository userRepository, WorkerRopository workerRopository) {
        this.userRepository = userRepository;
        this.workerRopository = workerRopository;
    }

    public List<TaskDTO> getAllTasksUser(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getAssignedTasks().stream()
                .map(this::convertTaskToDTO)
                .toList();
    }

    public TaskDTO convertTaskToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setPriority(task.getPriority());
        if (task.getStatus() != null){
            taskDTO.setStatus(task.getStatus());
            taskDTO.setDescription(task.getDescription());
        }
        else {//todo тут стало намного интересней, надо реализовать при нажатии "Выполнено" задача должна автоматически переходить на следующий этап , если он существует.
            taskDTO.setStatus(enums.Status.WAITING);
            taskDTO.setDescription("");
        }
        return taskDTO;
    }
}
