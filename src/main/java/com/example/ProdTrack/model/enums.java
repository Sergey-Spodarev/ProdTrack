package com.example.ProdTrack.model;

import lombok.Data;

@Data
public class enums {
    public enum Stage {
        PREPARATION, ASSEMBLING, CONTROL, PACKAGING
    }

    public enum Status {
        WAITING, WORK, COMPLETED
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}
