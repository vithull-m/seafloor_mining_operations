package com.seafloor.mining.dto;

import com.seafloor.mining.entity.MiningMission;
import com.seafloor.mining.entity.MiningSite;
import java.util.List;
import java.util.Map;

public class DashboardStatsDTO {

    private long totalDrones;
    private long activeDrones;
    private long totalMiningSites;
    private long activeMissions;
    private double totalMineralQuantity;
    private Map<String, Long> droneStatusBreakdown;
    private List<MiningMission> recentMissions;
    private List<MiningSite> miningSitesOverview;

    public DashboardStatsDTO() {
    }

    public DashboardStatsDTO(long totalDrones, long activeDrones, long totalMiningSites, long activeMissions,
                             double totalMineralQuantity, Map<String, Long> droneStatusBreakdown,
                             List<MiningMission> recentMissions, List<MiningSite> miningSitesOverview) {
        this.totalDrones = totalDrones;
        this.activeDrones = activeDrones;
        this.totalMiningSites = totalMiningSites;
        this.activeMissions = activeMissions;
        this.totalMineralQuantity = totalMineralQuantity;
        this.droneStatusBreakdown = droneStatusBreakdown;
        this.recentMissions = recentMissions;
        this.miningSitesOverview = miningSitesOverview;
    }

    public long getTotalDrones() {
        return totalDrones;
    }

    public void setTotalDrones(long totalDrones) {
        this.totalDrones = totalDrones;
    }

    public long getActiveDrones() {
        return activeDrones;
    }

    public void setActiveDrones(long activeDrones) {
        this.activeDrones = activeDrones;
    }

    public long getTotalMiningSites() {
        return totalMiningSites;
    }

    public void setTotalMiningSites(long totalMiningSites) {
        this.totalMiningSites = totalMiningSites;
    }

    public long getActiveMissions() {
        return activeMissions;
    }

    public void setActiveMissions(long activeMissions) {
        this.activeMissions = activeMissions;
    }

    public double getTotalMineralQuantity() {
        return totalMineralQuantity;
    }

    public void setTotalMineralQuantity(double totalMineralQuantity) {
        this.totalMineralQuantity = totalMineralQuantity;
    }

    public Map<String, Long> getDroneStatusBreakdown() {
        return droneStatusBreakdown;
    }

    public void setDroneStatusBreakdown(Map<String, Long> droneStatusBreakdown) {
        this.droneStatusBreakdown = droneStatusBreakdown;
    }

    public List<MiningMission> getRecentMissions() {
        return recentMissions;
    }

    public void setRecentMissions(List<MiningMission> recentMissions) {
        this.recentMissions = recentMissions;
    }

    public List<MiningSite> getMiningSitesOverview() {
        return miningSitesOverview;
    }

    public void setMiningSitesOverview(List<MiningSite> miningSitesOverview) {
        this.miningSitesOverview = miningSitesOverview;
    }
}
