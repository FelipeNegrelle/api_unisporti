package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.UserPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlan, Integer> {
}
