package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.FinancialMovementService;
import com.unisporti.api_unisporti.vo.FinancialMovementVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/secure/admin/financial-movement", "/api/secure/manager/financial-movement"})
public class FinancialMovementController {
    private final FinancialMovementService financialMovementService;

    public FinancialMovementController(FinancialMovementService financialMovementService) {
        this.financialMovementService = financialMovementService;
    }

    @PostMapping
    public FinancialMovementVO create(@RequestBody FinancialMovementVO movement) {
        try {
            return financialMovementService.create(movement);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar movimento financeiro: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public FinancialMovementVO findById(@PathVariable Integer id) {
        try {
            return financialMovementService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar movimento financeiro: " + e.getMessage());
        }
    }

    @GetMapping
    public List<FinancialMovementVO> findAll() {
        return financialMovementService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            financialMovementService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar movimento financeiro: " + e.getMessage());
        }
    }
}
