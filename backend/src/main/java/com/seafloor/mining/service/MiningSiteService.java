package com.seafloor.mining.service;

import com.seafloor.mining.dto.MiningSiteDTO;
import com.seafloor.mining.entity.MiningSite;
import com.seafloor.mining.exception.ResourceNotFoundException;
import com.seafloor.mining.repository.MiningSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiningSiteService {

    private final MiningSiteRepository miningSiteRepository;

    @Autowired
    public MiningSiteService(MiningSiteRepository miningSiteRepository) {
        this.miningSiteRepository = miningSiteRepository;
    }

    public List<MiningSite> getAllMiningSites() {
        return miningSiteRepository.findAll();
    }

    public MiningSite getMiningSiteById(Long id) {
        return miningSiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mining site not found with id: " + id));
    }

    public MiningSite createMiningSite(MiningSiteDTO dto) {
        MiningSite site = new MiningSite();
        site.setSiteName(dto.getSiteName().trim());
        site.setLocation(dto.getLocation().trim());
        site.setDepth(dto.getDepth());
        site.setStatus(dto.getStatus().toUpperCase().trim());
        return miningSiteRepository.save(site);
    }

    public MiningSite updateMiningSite(Long id, MiningSiteDTO dto) {
        MiningSite site = getMiningSiteById(id);
        site.setSiteName(dto.getSiteName().trim());
        site.setLocation(dto.getLocation().trim());
        site.setDepth(dto.getDepth());
        site.setStatus(dto.getStatus().toUpperCase().trim());
        return miningSiteRepository.save(site);
    }

    public void deleteMiningSite(Long id) {
        MiningSite site = getMiningSiteById(id);
        miningSiteRepository.delete(site);
    }
}
