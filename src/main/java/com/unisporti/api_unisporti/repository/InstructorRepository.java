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
            select
            u.id_user,
            m.id_modality,
            u.first_name,
            u.last_name,
            m.description as modality_description
            from users u
            inner join modality m on m.id_modality = (select p.id_modality from plan p where p.id_plan = (select up.id_plan from user_plan up where up.id_user = u.id_user))
            inner join instructor i on i.id_instructor = m.id_instructor
            where i.id_instructor = :idInstructor
            """, nativeQuery = true)
    List<Object[]> findAthletesByInstructor(@Param("idInstructor") Integer idInstructor);
}
