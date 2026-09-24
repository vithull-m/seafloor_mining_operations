package com.seafloor.mining.controller;

import com.seafloor.mining.dto.MineralDTO;
import com.seafloor.mining.entity.Mineral;
import com.seafloor.mining.service.MineralService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/minerals")
public class MineralController {

    private final MineralService mineralService;

    @Autowired
    public MineralController(MineralService mineralService) {
        this.mineralService = mineralService;
    }

    @GetMapping
    public ResponseEntity<List<Mineral>> getAllMinerals() {
        return ResponseEntity.ok(mineralService.getAllMinerals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mineral> getMineralById(@PathVariable Long id) {
        return ResponseEntity.ok(mineralService.getMineralById(id));
    }

    @PostMapping
    public ResponseEntity<Mineral> createMineral(@Valid @RequestBody MineralDTO dto) {
        Mineral created = mineralService.createMineral(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mineral> updateMineral(@PathVariable Long id, @Valid @RequestBody MineralDTO dto) {
        Mineral updated = mineralService.updateMineral(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMineral(@PathVariable Long id) {
        mineralService.deleteMineral(id);
        return ResponseEntity.noContent().build();
    }
}
