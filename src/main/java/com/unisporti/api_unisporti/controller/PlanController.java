package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.PlanService;
import com.unisporti.api_unisporti.vo.PlanVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/secure/admin/plan", "/api/secure/manager/plan"})
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public PlanVO create(@RequestBody PlanVO plan) {
        try {
            return planService.create(plan);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar plano: " + e.getMessage());
        }
    }

    @PutMapping
    public PlanVO update(@RequestBody PlanVO plan) {
        try {
            System.out.println(plan);
            return planService.update(plan);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar plano: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PlanVO findById(@PathVariable Integer id) {
        try {
            return planService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar plano: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        try {
            return planService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar plano: " + e.getMessage());
        }
    }

    @GetMapping
    public List<PlanVO> findAll() {
        return planService.findAll();
    }
}
