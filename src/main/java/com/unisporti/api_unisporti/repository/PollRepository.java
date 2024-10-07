package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Integer> {

}
