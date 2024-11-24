package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.Instructor;
import com.unisporti.api_unisporti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    boolean existsByDegreeNameAndIdInstructor(String degreeName, Integer idInstructor);

    Optional<Instructor> findByUserIdUser(Integer userIdUser);

    boolean existsByUser(User user);
}
