package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.InstructorService;
import com.unisporti.api_unisporti.vo.InstructorAthleteVO;
import com.unisporti.api_unisporti.vo.InstructorVO;
import com.unisporti.api_unisporti.vo.ModalityVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/secure/admin/instructor", "/api/secure/manager/instructor", "/api/secure/instructor/instructor"})
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public InstructorVO create(@RequestBody InstructorVO instructor) {
        try {
            return instructorService.create(instructor);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar instrutor: " + e.getMessage());
        }
    }

    @PutMapping
    public InstructorVO update(@RequestBody InstructorVO instructor) {
        try {
            return instructorService.update(instructor);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar instrutor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        try {
            return instructorService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar instrutor: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public InstructorVO findById(@PathVariable Integer id) {
        try {
            return instructorService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar instrutor: " + e.getMessage());
        }
    }

    @GetMapping
    public List<InstructorVO> findAll() {
        return instructorService.findAll();
    }

    @GetMapping("/modalities")
    public List<ModalityVO> findModalities() {
        return instructorService.getModalities();
    }

    @GetMapping("/athletes")
    public List<InstructorAthleteVO> findAthletes() {
        return instructorService.getAthletes();
    }
}