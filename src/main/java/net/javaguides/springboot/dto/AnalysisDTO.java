package net.javaguides.springboot.dto;

import java.util.List;

import lombok.Data;
@Data
public class AnalysisDTO {
    private String state;
    private List<String> response;
    private String detail;

    // Getters and setters
}
