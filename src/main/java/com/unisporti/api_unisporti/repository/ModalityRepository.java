package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.Modality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModalityRepository extends JpaRepository<Modality, Integer> {
    Modality findByDescription(String description);

    Modality findByDescriptionAndIdModalityIsNot(String description, Integer idModality);

    List<Modality> findByInstructorIdInstructor(Integer idInstructor);
}
