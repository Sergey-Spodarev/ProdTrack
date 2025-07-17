package com.example.ProdTrack.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private enums.Stage stage;
    @Enumerated(EnumType.STRING)
    private enums.Status status;
    @Enumerated(EnumType.STRING)
    private enums.Priority priority;

    private LocalDate startDate;
    private LocalDate endDate;
    private String comment;

    @ManyToOne
    private Users created;

    @ManyToOne
    private Users assigned;
}
