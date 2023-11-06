package net.javaguides.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LicenseDTO {
	@JsonProperty("license")
    private LicenseInfoDTO license;

    // Getters and setters
}

