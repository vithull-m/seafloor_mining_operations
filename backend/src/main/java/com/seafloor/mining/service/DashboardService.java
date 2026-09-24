package com.seafloor.mining.service;

import com.seafloor.mining.dto.DashboardStatsDTO;
import com.seafloor.mining.entity.Drone;
import com.seafloor.mining.entity.MiningMission;
import com.seafloor.mining.entity.MiningSite;
import com.seafloor.mining.repository.DroneRepository;
import com.seafloor.mining.repository.MineralRepository;
import com.seafloor.mining.repository.MiningMissionRepository;
import com.seafloor.mining.repository.MiningSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final DroneRepository droneRepository;
    private final MiningSiteRepository miningSiteRepository;
    private final MiningMissionRepository miningMissionRepository;
    private final MineralRepository mineralRepository;

    @Autowired
    public DashboardService(DroneRepository droneRepository,
                            MiningSiteRepository miningSiteRepository,
                            MiningMissionRepository miningMissionRepository,
                            MineralRepository mineralRepository) {
        this.droneRepository = droneRepository;
        this.miningSiteRepository = miningSiteRepository;
        this.miningMissionRepository = miningMissionRepository;
        this.mineralRepository = mineralRepository;
    }

    public DashboardStatsDTO getDashboardStats() {
        long totalDrones = droneRepository.count();
        long activeDrones = droneRepository.countByStatus("ACTIVE");
        long totalMiningSites = miningSiteRepository.count();
        long activeMissions = miningMissionRepository.countByStatus("IN_PROGRESS");
        Double totalMineralQty = mineralRepository.getTotalQuantity();
        double quantity = (totalMineralQty != null) ? totalMineralQty : 0.0;

        List<Drone> allDrones = droneRepository.findAll();
        Map<String, Long> statusBreakdown = new HashMap<>();
        for (Drone drone : allDrones) {
            statusBreakdown.put(drone.getStatus(), statusBreakdown.getOrDefault(drone.getStatus(), 0L) + 1L);
        }

        List<MiningMission> recentMissions = miningMissionRepository.findTop5ByOrderByIdDesc();
        List<MiningSite> sitesOverview = miningSiteRepository.findAll();

        return new DashboardStatsDTO(
                totalDrones,
                activeDrones,
                totalMiningSites,
                activeMissions,
                quantity,
                statusBreakdown,
                recentMissions,
                sitesOverview
        );
    }
}
