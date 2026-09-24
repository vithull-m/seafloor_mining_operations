package com.seafloor.mining.service;

import com.seafloor.mining.dto.MineralDTO;
import com.seafloor.mining.entity.Mineral;
import com.seafloor.mining.exception.ResourceNotFoundException;
import com.seafloor.mining.repository.MineralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MineralService {

    private final MineralRepository mineralRepository;

    @Autowired
    public MineralService(MineralRepository mineralRepository) {
        this.mineralRepository = mineralRepository;
    }

    public List<Mineral> getAllMinerals() {
        return mineralRepository.findAll();
    }

    public Mineral getMineralById(Long id) {
        return mineralRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mineral not found with id: " + id));
    }

    public Mineral createMineral(MineralDTO dto) {
        Mineral mineral = new Mineral();
        mineral.setMineralName(dto.getMineralName().trim());
        mineral.setType(dto.getType().trim());
        mineral.setQuantity(dto.getQuantity());
        mineral.setUnit(dto.getUnit().trim());
        return mineralRepository.save(mineral);
    }

    public Mineral updateMineral(Long id, MineralDTO dto) {
        Mineral mineral = getMineralById(id);
        mineral.setMineralName(dto.getMineralName().trim());
        mineral.setType(dto.getType().trim());
        mineral.setQuantity(dto.getQuantity());
        mineral.setUnit(dto.getUnit().trim());
        return mineralRepository.save(mineral);
    }

    public void deleteMineral(Long id) {
        Mineral mineral = getMineralById(id);
        mineralRepository.delete(mineral);
    }
}
