package com.unisporti.api_unisporti.repository;

import com.unisporti.api_unisporti.model.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Integer> {
    @Query(value = """
            select
                fm.id_financial_movement,
                fm.value,
                p.name as plan_name,
                m.description as modality_name,
                TO_CHAR(fm.date_time_payment, 'DD/MM/YYYY') AS payment_date
            from
                financial_movement fm
            inner join
                user_plan up on fm.id_user_plan = up.id_user_plan
            inner join
                plan p on up.id_plan = p.id_plan
            inner join
                modality m on p.id_modality = m.id_modality
            where
                up.id_user = :userId;
            """, nativeQuery = true)
    List<Object[]> findPaymentsByUserId(@Param("userId") Integer userId);

}
