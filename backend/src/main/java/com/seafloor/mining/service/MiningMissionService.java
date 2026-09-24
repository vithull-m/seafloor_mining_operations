package com.seafloor.mining.service;

import com.seafloor.mining.dto.MiningMissionDTO;
import com.seafloor.mining.entity.Drone;
import com.seafloor.mining.entity.MiningMission;
import com.seafloor.mining.entity.MiningSite;
import com.seafloor.mining.exception.ResourceNotFoundException;
import com.seafloor.mining.repository.DroneRepository;
import com.seafloor.mining.repository.MiningMissionRepository;
import com.seafloor.mining.repository.MiningSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiningMissionService {

    private final MiningMissionRepository miningMissionRepository;
    private final DroneRepository droneRepository;
    private final MiningSiteRepository miningSiteRepository;

    @Autowired
    public MiningMissionService(MiningMissionRepository miningMissionRepository,
                                DroneRepository droneRepository,
                                MiningSiteRepository miningSiteRepository) {
        this.miningMissionRepository = miningMissionRepository;
        this.droneRepository = droneRepository;
        this.miningSiteRepository = miningSiteRepository;
    }

    public List<MiningMission> getAllMissions() {
        return miningMissionRepository.findAll();
    }

    public MiningMission getMissionById(Long id) {
        return miningMissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mission not found with id: " + id));
    }

    public MiningMission createMission(MiningMissionDTO dto) {
        Drone drone = droneRepository.findById(dto.getDroneId())
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found with id: " + dto.getDroneId()));

        MiningSite site = miningSiteRepository.findById(dto.getMiningSiteId())
                .orElseThrow(() -> new ResourceNotFoundException("Mining site not found with id: " + dto.getMiningSiteId()));

        MiningMission mission = new MiningMission();
        mission.setMissionName(dto.getMissionName().trim());
        mission.setDrone(drone);
        mission.setMiningSite(site);
        mission.setStartDate(dto.getStartDate());
        mission.setStatus(dto.getStatus().toUpperCase().trim());

        return miningMissionRepository.save(mission);
    }

    public MiningMission updateMission(Long id, MiningMissionDTO dto) {
        MiningMission mission = getMissionById(id);

        Drone drone = droneRepository.findById(dto.getDroneId())
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found with id: " + dto.getDroneId()));

        MiningSite site = miningSiteRepository.findById(dto.getMiningSiteId())
                .orElseThrow(() -> new ResourceNotFoundException("Mining site not found with id: " + dto.getMiningSiteId()));

        mission.setMissionName(dto.getMissionName().trim());
        mission.setDrone(drone);
        mission.setMiningSite(site);
        mission.setStartDate(dto.getStartDate());
        mission.setStatus(dto.getStatus().toUpperCase().trim());

        return miningMissionRepository.save(mission);
    }

    public void deleteMission(Long id) {
        MiningMission mission = getMissionById(id);
        miningMissionRepository.delete(mission);
    }
}
