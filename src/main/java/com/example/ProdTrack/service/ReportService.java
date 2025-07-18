package com.example.ProdTrack.service;

import com.example.ProdTrack.model.enums;
import com.example.ProdTrack.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ReportService {
    private final TaskRepository taskRepository;
    public ReportService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Map<String, Long> getCountStage(){
        Map<String, Long> countStage = new HashMap<>();
        countStage.put(enums.Stage.PREPARATION.toString(), taskRepository.countByStage(enums.Stage.PREPARATION));
        countStage.put(enums.Stage.ASSEMBLING.toString(), taskRepository.countByStage(enums.Stage.ASSEMBLING));
        countStage.put(enums.Stage.CONTROL.toString(), taskRepository.countByStage(enums.Stage.CONTROL));
        countStage.put(enums.Stage.PACKAGING.toString(), taskRepository.countByStage(enums.Stage.PACKAGING));
        countStage.put(enums.Stage.COMPLETED.toString(), taskRepository.countByStage(enums.Stage.COMPLETED));
        return countStage;
    }

    public Map<String, Long> getCountStatus(){
        Map<String, Long> countStatus = new HashMap<>();
        countStatus.put(enums.Status.WAITING.toString(), taskRepository.countByStatus(enums.Status.WAITING));
        countStatus.put(enums.Status.WORK.toString(), taskRepository.countByStatus(enums.Status.WORK));
        countStatus.put(enums.Status.COMPLETED.toString(), taskRepository.countByStatus(enums.Status.COMPLETED));
        return countStatus;
    }

    public Map<String, Long> getCountPriority(){
        Map<String, Long> countPriority = new HashMap<>();
        countPriority.put(enums.Priority.LOW.toString(), taskRepository.countByPriority(enums.Priority.LOW));
        countPriority.put(enums.Priority.MEDIUM.toString(), taskRepository.countByPriority(enums.Priority.MEDIUM));
        countPriority.put(enums.Priority.HIGH.toString(), taskRepository.countByPriority(enums.Priority.HIGH));
        return countPriority;
    }
}
