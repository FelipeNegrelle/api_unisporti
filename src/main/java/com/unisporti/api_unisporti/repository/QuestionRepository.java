package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
