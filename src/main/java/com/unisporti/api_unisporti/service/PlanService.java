package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.config.Util;
import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Plan;
import com.unisporti.api_unisporti.repository.ModalityRepository;
import com.unisporti.api_unisporti.repository.PlanRepository;
import com.unisporti.api_unisporti.vo.PlanVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final ModalityRepository modalityRepository;

    public PlanService(PlanRepository planRepository, ModalityRepository modalityRepository) {
        this.planRepository = planRepository;
        this.modalityRepository = modalityRepository;
    }

    private Optional<Map<String, String>> validate(PlanVO plan) {
        Map<String, String> errors = new HashMap<>();

        if (plan.getIdPlan() != null && planRepository.countByNameAndIdPlanIsNot(plan.getName(), plan.getIdPlan()) > 0) {
            errors.put("name", "Já existe um plano com este nome.");
        }

        if (plan.getPriceCents() <= 0) {
            errors.put("price_cents", "O preço deve ser maior que zero.");
        }

        if (plan.getDurationDays() <= 0) {
            errors.put("duration_days", "A duração deve ser maior que zero.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public PlanVO create(PlanVO plan) throws Exception {
        if (plan != null) {
            Map<String, String> errors = validate(plan).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                Plan entity = new Plan();
                entity.setName(plan.getName());
                entity.setModality(modalityRepository.findById(plan.getIdModality()).orElseThrow(() -> new NotFoundException("Modalidade não encontrada.")));
                entity.setPriceCents(plan.getPriceCents());
                entity.setDurationDays(plan.getDurationDays());
                entity.setActive(plan.getActive());

                Plan savedPlan = planRepository.save(entity);

                return new PlanVO(savedPlan.getIdPlan(), savedPlan.getModality().getIdModality(), savedPlan.getName(), savedPlan.getPriceCents(), savedPlan.getDurationDays(), savedPlan.getActive());
            } else {
                throw new MalformedRequestException(Util.formatErrorMessage(errors));
            }
        } else {
            throw new MalformedRequestException("Plano não pode ser nulo.");
        }
    }

    public PlanVO update(PlanVO plan) throws Exception {
        if (plan != null) {
            Map<String, String> errors = validate(plan).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                Plan entity = planRepository.findById(plan.getIdPlan()).orElseThrow(() -> new NotFoundException("Plano não encontrado."));
                entity.setName(plan.getName());
                entity.setModality(modalityRepository.findById(plan.getIdModality()).orElseThrow(() -> new NotFoundException("Modalidade não encontrada.")));
                entity.setPriceCents(plan.getPriceCents());
                entity.setDurationDays(plan.getDurationDays());
                entity.setActive(plan.getActive());

                Plan updatedPlan = planRepository.save(entity);

                return new PlanVO(updatedPlan.getIdPlan(), updatedPlan.getModality().getIdModality(), updatedPlan.getName(), updatedPlan.getPriceCents(), updatedPlan.getDurationDays(), updatedPlan.getActive());
            } else {
                throw new MalformedRequestException(Util.formatErrorMessage(errors));
            }
        } else {
            throw new MalformedRequestException("Plano não pode ser nulo.");
        }
    }

    public List<PlanVO> findAll() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream().map(p -> new PlanVO(p.getIdPlan(), p.getModality().getIdModality(), p.getName(), p.getPriceCents(), p.getDurationDays(), p.getActive())).toList();
    }

    public PlanVO findById(Integer id) {
        if (id != null) {
            Plan entity = planRepository.findById(id).orElseThrow(() -> new NotFoundException("Plano não encontrado."));
            return new PlanVO(entity.getIdPlan(), entity.getModality().getIdModality(), entity.getName(), entity.getPriceCents(), entity.getDurationDays(), entity.getActive());
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) {
        if (id != null) {
            Plan entity = planRepository.findById(id).orElseThrow(() -> new NotFoundException("Plano não encontrado."));
            planRepository.delete(entity);
            return true;
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }
}
