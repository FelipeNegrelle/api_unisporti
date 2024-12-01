package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.FinancialMovement;
import com.unisporti.api_unisporti.model.User;
import com.unisporti.api_unisporti.model.UserContext;
import com.unisporti.api_unisporti.model.UserPlan;
import com.unisporti.api_unisporti.repository.FinancialMovementRepository;
import com.unisporti.api_unisporti.repository.UserPlanRepository;
import com.unisporti.api_unisporti.repository.UserRepository;
import com.unisporti.api_unisporti.vo.FinancialMovementVO;
import com.unisporti.api_unisporti.vo.MyPaymentsVO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinancialMovementService {
    private final FinancialMovementRepository financialMovementRepository;
    private final UserPlanRepository userPlanRepository;
    private final UserRepository userRepository;

    public FinancialMovementService(FinancialMovementRepository financialMovementRepository, UserPlanRepository userPlanRepository, UserRepository userRepository) {
        this.financialMovementRepository = financialMovementRepository;
        this.userPlanRepository = userPlanRepository;
        this.userRepository = userRepository;
    }

    private Optional<Map<String, String>> validate(FinancialMovementVO movementVO) {
        Map<String, String> errors = new HashMap<>();

        if (movementVO.getIdUserPlan() != null && !userPlanRepository.existsById(movementVO.getIdUserPlan())) {
            errors.put("idPlan", "Plano não encontrado.");
        }

        if (movementVO.getValue() == null || movementVO.getValue() <= 0) {
            errors.put("value", "Valor do movimento tem de ser maior que 0.");
        }

        if (movementVO.getDateTimePayment() == null) {
            errors.put("dateTimePayment", "Data/Hora do pagamento não podem ser nulos.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public FinancialMovementVO create(FinancialMovementVO movementVO) throws Exception {
        if (movementVO != null) {
            final Map<String, String> errors = validate(movementVO).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final FinancialMovement movement = new FinancialMovement();
                if (movementVO.getIdUserPlan() != null) {
                    final UserPlan plan = userPlanRepository.findById(movementVO.getIdUserPlan()).orElseThrow(() -> new NotFoundException("Plano de usuário não encontrado."));
                    movement.setUserPlan(plan);
                }
                movement.setValue(movementVO.getValue());
                movement.setDateTimePayment(movementVO.getDateTimePayment());

                final FinancialMovement fm = financialMovementRepository.save(movement);

                return new FinancialMovementVO(fm.getIdFinancialMovement(), fm.getUserPlan() != null ? fm.getUserPlan().getIdUserPlan() : null, fm.getValue(), fm.getDateTimePayment());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Movimento financeiro não pode ser nulo.");
        }
    }

    public FinancialMovementVO findById(Integer id) throws Exception {
        FinancialMovement movement = financialMovementRepository.findById(id).orElseThrow(() -> new NotFoundException("Movimento financeiro não encontrado."));

        return new FinancialMovementVO(movement.getIdFinancialMovement(), movement.getUserPlan() != null ? movement.getUserPlan().getIdUserPlan() : null, movement.getValue(), movement.getDateTimePayment());
    }

    public List<FinancialMovementVO> findAll() {
        return financialMovementRepository.findAll().stream().map(movement -> new FinancialMovementVO(movement.getIdFinancialMovement(), movement.getUserPlan() != null ? movement.getUserPlan().getIdUserPlan() : null, movement.getValue(), movement.getDateTimePayment())).toList();
    }

    public List<MyPaymentsVO> getMyPayments() {
        final UserContext context = UserContext.getCurrentUser();

        final User user = userRepository.findById(context.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        // Busca os pagamentos do usuário
        final List<Object[]> raw = financialMovementRepository.findPaymentsByUserId(user.getIdUser());

        // Mapeia o resultado da query para MyPaymentsVO
        return raw.stream()
                .map(result -> new MyPaymentsVO(
                        (Integer) result[0], // id_financial_movement
                        (Float) result[1], // value
                        (String) result[2], // plan_name
                        (String) result[3], // modality_name
                        (String) result[4] // date_time_payment
                ))
                .collect(Collectors.toList());
    }

    public void delete(Integer id) throws Exception {
        FinancialMovement movement = financialMovementRepository.findById(id).orElseThrow(() -> new NotFoundException("Movimento financeiro não encontrado."));

        financialMovementRepository.delete(movement);
    }
}
