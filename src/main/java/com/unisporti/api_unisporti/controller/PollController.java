package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.model.UserContext;
import com.unisporti.api_unisporti.service.PollService;
import com.unisporti.api_unisporti.vo.PollVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/secure/admin/poll", "/api/secure/manager/poll", "/api/secure/instructor/poll"})
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public PollVO create(@RequestBody PollVO poll) {
        try {
            final UserContext context = UserContext.getCurrentUser();
            return pollService.create(poll);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar enquete " + e.getMessage());
        }
    }

    @PutMapping
    public PollVO update(@RequestBody PollVO poll) {
        try {
            return pollService.update(poll);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar enquete " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PollVO findById(@PathVariable Integer id) {
        try {
            return pollService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar enquete " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        try {
            return pollService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar enquete " + e.getMessage());
        }
    }
}
