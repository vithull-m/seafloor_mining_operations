package com.seafloor.mining.controller;

import com.seafloor.mining.dto.DroneDTO;
import com.seafloor.mining.entity.Drone;
import com.seafloor.mining.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones() {
        return ResponseEntity.ok(droneService.getAllDrones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDroneById(@PathVariable Long id) {
        return ResponseEntity.ok(droneService.getDroneById(id));
    }

    @PostMapping
    public ResponseEntity<Drone> createDrone(@Valid @RequestBody DroneDTO dto) {
        Drone created = droneService.createDrone(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drone> updateDrone(@PathVariable Long id, @Valid @RequestBody DroneDTO dto) {
        Drone updated = droneService.updateDrone(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrone(@PathVariable Long id) {
        droneService.deleteDrone(id);
        return ResponseEntity.noContent().build();
    }
}
