package com.seafloor.mining.repository;

import com.seafloor.mining.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByStatus(String status);
    long countByStatus(String status);
}
