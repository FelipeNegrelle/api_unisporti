package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Integer> {
}
