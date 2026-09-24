package com.seafloor.mining.controller;

import com.seafloor.mining.dto.MiningMissionDTO;
import com.seafloor.mining.entity.MiningMission;
import com.seafloor.mining.service.MiningMissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MiningMissionController {

    private final MiningMissionService miningMissionService;

    @Autowired
    public MiningMissionController(MiningMissionService miningMissionService) {
        this.miningMissionService = miningMissionService;
    }

    @GetMapping
    public ResponseEntity<List<MiningMission>> getAllMissions() {
        return ResponseEntity.ok(miningMissionService.getAllMissions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiningMission> getMissionById(@PathVariable Long id) {
        return ResponseEntity.ok(miningMissionService.getMissionById(id));
    }

    @PostMapping
    public ResponseEntity<MiningMission> createMission(@Valid @RequestBody MiningMissionDTO dto) {
        MiningMission created = miningMissionService.createMission(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiningMission> updateMission(@PathVariable Long id, @Valid @RequestBody MiningMissionDTO dto) {
        MiningMission updated = miningMissionService.updateMission(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long id) {
        miningMissionService.deleteMission(id);
        return ResponseEntity.noContent().build();
    }
}
