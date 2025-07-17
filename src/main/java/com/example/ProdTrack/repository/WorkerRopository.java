package com.example.ProdTrack.repository;

import com.example.ProdTrack.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkerRopository extends CrudRepository<Task, Long> {
    Optional<Task> findById(Long id);
}
