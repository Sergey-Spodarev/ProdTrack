package com.example.ProdTrack.service;

import com.example.ProdTrack.DTO.TaskDTO;
import com.example.ProdTrack.DTO.UserDTO;
import com.example.ProdTrack.model.Task;
import com.example.ProdTrack.model.Users;
import com.example.ProdTrack.model.enums;
import com.example.ProdTrack.repository.TaskRepository;
import com.example.ProdTrack.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class AdminService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public AdminService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return tasks.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<UserDTO> getAllUsersWithTask(){
        List<Users> users = userRepository.findAllByRole("USER");
        return users.stream()
                .map(this::convertTODTO)
                .toList();
    }

    public TaskDTO addTask(TaskDTO taskDTO, String username) {
        Task newTask = new Task();

        newTask.setTitle(taskDTO.getTitle());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setPriority(taskDTO.getPriority());
        newTask.setDescription(taskDTO.getDescription());

        newTask.setStatus(enums.Status.WAITING);
        newTask.setStage(enums.Stage.PREPARATION);

        newTask.setStartDate(taskDTO.getStartDate());
        newTask.setEndDate(taskDTO.getEndDate());
        newTask.setComment("");

        newTask.setCreated(getUser(username));
        newTask.setAssigned(getUser(taskDTO.getAssigneeUsername()));

        return convertToDTO(taskRepository.save(newTask));
    }

    public Users getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public TaskDTO updateTask(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId()).orElse(null);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setDescription(taskDTO.getDescription());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        return convertToDTO(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public UserDTO convertTODTO(Users user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setComment(task.getComment());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setStage(task.getStage());
        taskDTO.setAssigneeUsername(task.getAssigned().getUsername());
        return taskDTO;
    }
}
