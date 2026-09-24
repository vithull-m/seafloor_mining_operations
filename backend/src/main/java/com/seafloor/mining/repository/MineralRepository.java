package com.seafloor.mining.repository;

import com.seafloor.mining.entity.Mineral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MineralRepository extends JpaRepository<Mineral, Long> {
    List<Mineral> findByType(String type);

    @Query("SELECT COALESCE(SUM(m.quantity), 0) FROM Mineral m")
    Double getTotalQuantity();
}
