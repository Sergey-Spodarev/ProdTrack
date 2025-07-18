package com.example.ProdTrack.controller;

import com.example.ProdTrack.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/by-stage")
    public Map<String, Long> getCountStage() {
        return reportService.getCountStage();
    }

    @GetMapping("/by-status")
    public Map<String, Long> getCountStatus() {
        return reportService.getCountStatus();
    }

    @GetMapping("/by-priority")
    public Map<String, Long> getCountPriority() {
        return reportService.getCountPriority();
    }
}
