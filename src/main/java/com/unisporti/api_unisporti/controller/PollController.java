package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.PollService;
import com.unisporti.api_unisporti.vo.PollVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/poll")
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    public PollVO create(PollVO poll) {
        try {
            return pollService.create(poll);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar enquete " + e.getMessage());
        }
    }

    public PollVO update(PollVO poll) {
        try {
            return pollService.update(poll);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar enquete " + e.getMessage());
        }
    }

    public PollVO findById(Integer id) {
        try {
            return pollService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar enquete " + e.getMessage());
        }
    }

    public Boolean delete(Integer id) {
        try {
            return pollService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar enquete " + e.getMessage());
        }
    }
}
