package com.seafloor.mining.repository;

import com.seafloor.mining.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByOperatorIdIgnoreCase(String operatorId);
    boolean existsByOperatorIdIgnoreCase(String operatorId);
}
