package net.javaguides.springboot.dto;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class dependencyDTO {
    @JsonProperty("artifact")
    private ArtifactDTO artifact;

    @JsonProperty("components")
    private List<ComponentDTO> components;

    @Data
    public static class ArtifactDTO {
        @JsonProperty("name")
        private String name;

        @JsonProperty("path")
        private String path;

        @JsonProperty("pkg_type")
        private String pkgType;

        @JsonProperty("sha256")
        private String sha256;

        @JsonProperty("component_id")
        private String componentId;
    }

    @Data
    public static class ComponentDTO {
        @JsonProperty("component_name")
        private String componentName;

        @JsonProperty("component_id")
        private String componentId;

        @JsonProperty("package_type")
        private String packageType;

        @JsonProperty("created")
        private Date created;
        @JsonProperty("components")
        private List<ComponentDTO> components;
    }
}
