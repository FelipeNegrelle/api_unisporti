package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.FinancialMovement;
import com.unisporti.api_unisporti.model.Plan;
import com.unisporti.api_unisporti.repository.FinancialMovementRepository;
import com.unisporti.api_unisporti.repository.PlanRepository;
import com.unisporti.api_unisporti.vo.FinancialMovementVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FinancialMovementService {
    private final FinancialMovementRepository financialMovementRepository;
    private final PlanRepository planRepository;

    public FinancialMovementService(FinancialMovementRepository financialMovementRepository, PlanRepository planRepository) {
        this.financialMovementRepository = financialMovementRepository;
        this.planRepository = planRepository;
    }

    private Optional<Map<String, String>> validate(FinancialMovementVO movementVO) {
        Map<String, String> errors = new HashMap<>();

        if (movementVO.getIdPlan() != null && !planRepository.existsById(movementVO.getIdPlan())) {
            errors.put("idPlan", "Plano não encontrado.");
        }

        if (movementVO.getValue() == null) {
            errors.put("value", "Valor não pode ser nulo.");
        }

        if (movementVO.getDateTimePayment() == null) {
            errors.put("dateTimePayment", "Data e hora do pagamento não podem ser nulos.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public FinancialMovementVO create(FinancialMovementVO movementVO) throws Exception {
        if (movementVO == null) {
            throw new MalformedRequestException("Movimento financeiro não pode ser nulo.");
        }

        FinancialMovement movement = new FinancialMovement();

        if (movementVO.getIdPlan() != null) {
            Plan plan = planRepository.findById(movementVO.getIdPlan())
                    .orElseThrow(() -> new NotFoundException("Plano não encontrado."));
            movement.setPlan(plan);
        }

        movement.setValue(movementVO.getValue());
        movement.setDateTimePayment(movementVO.getDateTimePayment());

        FinancialMovement savedMovement = financialMovementRepository.save(movement);

        return new FinancialMovementVO(savedMovement.getIdFinancialMovement(),
                savedMovement.getPlan() != null ? savedMovement.getPlan().getIdPlan() : null,
                savedMovement.getValue(),
                savedMovement.getDateTimePayment());
    }

    public FinancialMovementVO findById(Integer id) throws Exception {
        FinancialMovement movement = financialMovementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movimento financeiro não encontrado."));

        return new FinancialMovementVO(movement.getIdFinancialMovement(),
                movement.getPlan() != null ? movement.getPlan().getIdPlan() : null,
                movement.getValue(),
                movement.getDateTimePayment());
    }

    public List<FinancialMovementVO> findAll() {
        return financialMovementRepository.findAll().stream()
                .map(movement -> new FinancialMovementVO(
                        movement.getIdFinancialMovement(),
                        movement.getPlan() != null ? movement.getPlan().getIdPlan() : null,
                        movement.getValue(),
                        movement.getDateTimePayment()))
                .toList();
    }

    public void delete(Integer id) throws Exception {
        FinancialMovement movement = financialMovementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movimento financeiro não encontrado."));

        financialMovementRepository.delete(movement);
    }
}
