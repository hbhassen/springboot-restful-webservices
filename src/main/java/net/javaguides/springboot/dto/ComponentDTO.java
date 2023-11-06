package net.javaguides.springboot.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ComponentDTO {
	@JsonProperty("bom-ref")
    private String bomRef;
    private String type;
    private String name;
    private String version;
    private List<HashDTO> hashes;
    @JsonProperty("licenses")
    private List<LicenseDTO> licenses;
    private String purl;

    // Getters and setters
}

