package com.seafloor.mining.service;

import com.seafloor.mining.dto.DroneDTO;
import com.seafloor.mining.entity.Drone;
import com.seafloor.mining.exception.ResourceNotFoundException;
import com.seafloor.mining.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {

    private final DroneRepository droneRepository;

    @Autowired
    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public Drone getDroneById(Long id) {
        return droneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found with id: " + id));
    }

    public Drone createDrone(DroneDTO dto) {
        Drone drone = new Drone();
        drone.setDroneName(dto.getDroneName().trim());
        drone.setModel(dto.getModel().trim());
        drone.setStatus(dto.getStatus().toUpperCase().trim());
        drone.setOperatingDepth(dto.getOperatingDepth());
        return droneRepository.save(drone);
    }

    public Drone updateDrone(Long id, DroneDTO dto) {
        Drone drone = getDroneById(id);
        drone.setDroneName(dto.getDroneName().trim());
        drone.setModel(dto.getModel().trim());
        drone.setStatus(dto.getStatus().toUpperCase().trim());
        drone.setOperatingDepth(dto.getOperatingDepth());
        return droneRepository.save(drone);
    }

    public void deleteDrone(Long id) {
        Drone drone = getDroneById(id);
        droneRepository.delete(drone);
    }
}
