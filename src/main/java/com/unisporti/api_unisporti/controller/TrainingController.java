package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.TrainingService;
import com.unisporti.api_unisporti.vo.TrainingVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/secure/admin/training", "/api/secure/manager/training", "/api/secure/instructor/training", "/api/secure/user/training"})
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping
    public TrainingVO create(@RequestBody TrainingVO training) {
        try {
            return trainingService.create(training);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar treinamento: " + e.getMessage());
        }
    }

    @GetMapping
    public List<TrainingVO> findAll() {
        return trainingService.findAll();
    }

    @GetMapping("/{id}")
    public TrainingVO findById(@PathVariable Integer id) {
        try {
            return trainingService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar treinamento: " + e.getMessage());
        }
    }

    @PutMapping
    public TrainingVO update(@RequestBody TrainingVO training) {
        try {
            return trainingService.update(training);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar treinamento: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        try {
            return trainingService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar treinamento: " + e.getMessage());
        }
    }
}
