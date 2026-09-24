package com.seafloor.mining.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mining_missions")
public class MiningMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_name", nullable = false)
    private String missionName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mining_site_id", nullable = false)
    private MiningSite miningSite;

    @Column(name = "start_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false)
    private String status;

    public MiningMission() {
    }

    public MiningMission(Long id, String missionName, Drone drone, MiningSite miningSite, LocalDate startDate, String status) {
        this.id = id;
        this.missionName = missionName;
        this.drone = drone;
        this.miningSite = miningSite;
        this.startDate = startDate;
        this.status = status;
    }

    public MiningMission(String missionName, Drone drone, MiningSite miningSite, LocalDate startDate, String status) {
        this.missionName = missionName;
        this.drone = drone;
        this.miningSite = miningSite;
        this.startDate = startDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public MiningSite getMiningSite() {
        return miningSite;
    }

    public void setMiningSite(MiningSite miningSite) {
        this.miningSite = miningSite;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
