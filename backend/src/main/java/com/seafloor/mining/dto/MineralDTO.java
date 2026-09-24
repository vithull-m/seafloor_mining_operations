package com.seafloor.mining.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class MineralDTO {

    private Long id;

    @NotBlank(message = "Mineral name is required")
    private String mineralName;

    @NotBlank(message = "Mineral type is required")
    private String type;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Double quantity;

    @NotBlank(message = "Unit is required")
    private String unit;

    public MineralDTO() {
    }

    public MineralDTO(Long id, String mineralName, String type, Double quantity, String unit) {
        this.id = id;
        this.mineralName = mineralName;
        this.type = type;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMineralName() {
        return mineralName;
    }

    public void setMineralName(String mineralName) {
        this.mineralName = mineralName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
