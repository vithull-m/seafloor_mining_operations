package com.seafloor.mining.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mining_sites")
public class MiningSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "site_name", nullable = false)
    private String siteName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Double depth;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "miningSite", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<MiningMission> missions = new ArrayList<>();

    public MiningSite() {
    }

    public MiningSite(Long id, String siteName, String location, Double depth, String status) {
        this.id = id;
        this.siteName = siteName;
        this.location = location;
        this.depth = depth;
        this.status = status;
    }

    public MiningSite(String siteName, String location, Double depth, String status) {
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

    public List<MiningMission> getMissions() {
        return missions;
    }

    public void setMissions(List<MiningMission> missions) {
        this.missions = missions;
    }
}
