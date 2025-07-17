package com.example.ProdTrack.DTO;

import com.example.ProdTrack.model.enums;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;

    private enums.Stage stage;
    private enums.Status status;
    private enums.Priority priority;

    private LocalDate startDate;
    private LocalDate endDate;
    private String comment;
}
