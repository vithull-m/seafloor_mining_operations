package com.seafloor.mining.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drone_name", nullable = false)
    private String droneName;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String status;

    @Column(name = "operating_depth", nullable = false)
    private Double operatingDepth;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<MiningMission> missions = new ArrayList<>();

    public Drone() {
    }

    public Drone(Long id, String droneName, String model, String status, Double operatingDepth) {
        this.id = id;
        this.droneName = droneName;
        this.model = model;
        this.status = status;
        this.operatingDepth = operatingDepth;
    }

    public Drone(String droneName, String model, String status, Double operatingDepth) {
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

    public List<MiningMission> getMissions() {
        return missions;
    }

    public void setMissions(List<MiningMission> missions) {
        this.missions = missions;
    }
}
