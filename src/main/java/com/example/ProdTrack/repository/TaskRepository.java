package com.example.ProdTrack.repository;

import com.example.ProdTrack.model.Task;
import com.example.ProdTrack.model.enums;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findById(Long id);

    Long countByStage(enums.Stage stage);
    Long countByStatus(enums.Status status);
    Long countByPriority(enums.Priority priority);
}
