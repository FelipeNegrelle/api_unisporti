package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    int countByNameAndIdPlanIsNot(String name, Integer idPlan);
}
