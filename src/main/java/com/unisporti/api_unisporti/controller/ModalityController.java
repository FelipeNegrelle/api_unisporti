package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.ModalityService;
import com.unisporti.api_unisporti.vo.ModalityVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/secure/admin/modality", "/api/secure/manager/modality", "/api/secure/instructor/modality"})
public class ModalityController {
    private final ModalityService modalityService;

    public ModalityController(ModalityService modalityService) {
        this.modalityService = modalityService;
    }

    @PostMapping
    public ModalityVO create(@RequestBody ModalityVO modality) {
        try {
            return modalityService.create(modality);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException("Erro ao criar modalidade: " + e.getMessage());
        }
    }

    @PutMapping
    public ModalityVO update(@RequestBody ModalityVO modality) {
        try {
            return modalityService.update(modality);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar modalidade: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ModalityVO findById(@PathVariable Integer id) {
        try {
            return modalityService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar modalidade: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        try {
            return modalityService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar modalidade: " + e.getMessage());
        }
    }

    @GetMapping
    public List<ModalityVO> findAll() {
        return modalityService.findAll();
    }
}
