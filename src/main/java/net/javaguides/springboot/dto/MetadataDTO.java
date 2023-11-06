package net.javaguides.springboot.dto;

import java.util.List;

import lombok.Data;
@Data
public class MetadataDTO {
    private String timestamp;
    private List<ToolDTO> tools;
    private ComponentDTO component;

    // Getters and setters
}

