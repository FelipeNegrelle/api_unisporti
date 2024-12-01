package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.TrainingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  TrainingUserRepository extends JpaRepository<TrainingUser, Integer> {
}
