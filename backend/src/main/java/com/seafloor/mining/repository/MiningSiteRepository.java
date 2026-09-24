package com.seafloor.mining.repository;

import com.seafloor.mining.entity.MiningSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiningSiteRepository extends JpaRepository<MiningSite, Long> {
    List<MiningSite> findByStatus(String status);
}
