package com.seafloor.mining.repository;

import com.seafloor.mining.entity.MiningMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiningMissionRepository extends JpaRepository<MiningMission, Long> {
    List<MiningMission> findByStatus(String status);
    long countByStatus(String status);
    List<MiningMission> findTop5ByOrderByIdDesc();
}
