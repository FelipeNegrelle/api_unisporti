package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.Instructor;
import com.unisporti.api_unisporti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    boolean existsByDegreeNameAndIdInstructor(String degreeName, Integer idInstructor);

    Optional<Instructor> findByUserIdUser(Integer userIdUser);

    boolean existsByUser(User user);

    @Query(value = """
            select\s
                tu.id_training,
                tu.id_user,
                u.first_name,
                u.last_name,
                t.description as training_description,
                m.description as modality_description
            from\s
                training_user tu
            inner join\s
                users u on u.id_user = tu.id_user
            inner join\s
                training t on t.id_training = tu.id_training
            inner join\s
                modality m on m.id_modality = t.id_modality
            where\s
                m.id_instructor = :idInstructor;
            """, nativeQuery = true)
    List<Object[]> findAthletesByInstructor(@Param("idInstructor") Integer idInstructor);
}
