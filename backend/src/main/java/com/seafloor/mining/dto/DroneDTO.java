package com.seafloor.mining.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DroneDTO {

    private Long id;

    @NotBlank(message = "Drone name is required")
    private String droneName;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Operating depth is required")
    @Positive(message = "Operating depth must be greater than zero")
    private Double operatingDepth;

    public DroneDTO() {
    }

    public DroneDTO(Long id, String droneName, String model, String status, Double operatingDepth) {
        this.id = id;
        this.droneName = droneName;
        this.model = model;
        this.status = status;
        this.operatingDepth = operatingDepth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDroneName() {
        return droneName;
    }

    public void setDroneName(String droneName) {
        this.droneName = droneName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getOperatingDepth() {
        return operatingDepth;
    }

    public void setOperatingDepth(Double operatingDepth) {
        this.operatingDepth = operatingDepth;
    }
}
