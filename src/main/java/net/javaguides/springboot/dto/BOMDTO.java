package net.javaguides.springboot.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class BOMDTO {
	
    private String bomFormat;
    private String specVersion;
    private String serialNumber;
    private int version;
    private MetadataDTO metadata;
    private List<ComponentDTO> components;
    private List<VulnerabilityDTO> vulnerabilities;

    // Getters and setters
}


