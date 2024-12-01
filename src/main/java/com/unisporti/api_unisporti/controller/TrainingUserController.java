package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.TrainingUserService;
import com.unisporti.api_unisporti.vo.TrainingUserVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/secure/admin/training-user", "/api/secure/manager/training-user"})
public class TrainingUserController {
    private final TrainingUserService trainingUserService;

    public TrainingUserController(TrainingUserService trainingUserService) {
        this.trainingUserService = trainingUserService;
    }

    @PostMapping
    public TrainingUserVO create(@RequestBody TrainingUserVO trainingUserVO) {
        try {
            return trainingUserService.create(trainingUserVO);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar associação: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public TrainingUserVO findById(@PathVariable Integer id) {
        try {
            return trainingUserService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar associação: " + e.getMessage());
        }
    }

    @GetMapping
    public List<TrainingUserVO> findAll() {
        return trainingUserService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            trainingUserService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar associação: " + e.getMessage());
        }
    }
}
