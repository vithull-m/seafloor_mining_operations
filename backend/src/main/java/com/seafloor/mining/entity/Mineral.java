package com.seafloor.mining.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "minerals")
public class Mineral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mineral_name", nullable = false)
    private String mineralName;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private String unit;

    public Mineral() {
    }

    public Mineral(Long id, String mineralName, String type, Double quantity, String unit) {
        this.id = id;
        this.mineralName = mineralName;
        this.type = type;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Mineral(String mineralName, String type, Double quantity, String unit) {
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
