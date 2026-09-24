package com.seafloor.mining.controller;

import com.seafloor.mining.dto.MiningSiteDTO;
import com.seafloor.mining.entity.MiningSite;
import com.seafloor.mining.service.MiningSiteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mining-sites")
public class MiningSiteController {

    private final MiningSiteService miningSiteService;

    @Autowired
    public MiningSiteController(MiningSiteService miningSiteService) {
        this.miningSiteService = miningSiteService;
    }

    @GetMapping
    public ResponseEntity<List<MiningSite>> getAllMiningSites() {
        return ResponseEntity.ok(miningSiteService.getAllMiningSites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiningSite> getMiningSiteById(@PathVariable Long id) {
        return ResponseEntity.ok(miningSiteService.getMiningSiteById(id));
    }

    @PostMapping
    public ResponseEntity<MiningSite> createMiningSite(@Valid @RequestBody MiningSiteDTO dto) {
        MiningSite created = miningSiteService.createMiningSite(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiningSite> updateMiningSite(@PathVariable Long id, @Valid @RequestBody MiningSiteDTO dto) {
        MiningSite updated = miningSiteService.updateMiningSite(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMiningSite(@PathVariable Long id) {
        miningSiteService.deleteMiningSite(id);
        return ResponseEntity.noContent().build();
    }
}
