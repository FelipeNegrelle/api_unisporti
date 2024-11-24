package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {
    Integer countByIdTrainingIsNot(Integer idTraining);
}
