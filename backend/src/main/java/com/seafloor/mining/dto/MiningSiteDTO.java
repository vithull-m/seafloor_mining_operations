package com.seafloor.mining.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MiningSiteDTO {

    private Long id;

    @NotBlank(message = "Site name is required")
    private String siteName;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Depth is required")
    @Positive(message = "Depth must be greater than zero")
    private Double depth;

    @NotBlank(message = "Status is required")
    private String status;

    public MiningSiteDTO() {
    }

    public MiningSiteDTO(Long id, String siteName, String location, Double depth, String status) {
        this.id = id;
        this.siteName = siteName;
        this.location = location;
        this.depth = depth;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
