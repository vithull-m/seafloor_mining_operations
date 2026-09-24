package com.seafloor.mining.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seafloor.mining.entity.Drone;
import com.seafloor.mining.entity.MiningSite;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class MiningMissionDTO {

    private Long id;

    @NotBlank(message = "Mission name is required")
    private String missionName;

    @NotNull(message = "Drone ID is required")
    private Long droneId;

    @NotNull(message = "Mining site ID is required")
    private Long miningSiteId;

    private Drone drone;
    private MiningSite miningSite;

    @NotNull(message = "Start date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotBlank(message = "Status is required")
    private String status;

    public MiningMissionDTO() {
    }

    public MiningMissionDTO(Long id, String missionName, Long droneId, Long miningSiteId, Drone drone, MiningSite miningSite, LocalDate startDate, String status) {
        this.id = id;
        this.missionName = missionName;
        this.droneId = droneId;
        this.miningSiteId = miningSiteId;
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

    public Long getDroneId() {
        return droneId;
    }

    public void setDroneId(Long droneId) {
        this.droneId = droneId;
    }

    public Long getMiningSiteId() {
        return miningSiteId;
    }

    public void setMiningSiteId(Long miningSiteId) {
        this.miningSiteId = miningSiteId;
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
