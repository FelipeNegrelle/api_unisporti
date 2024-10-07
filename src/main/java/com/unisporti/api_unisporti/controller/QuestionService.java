package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.vo.QuestionVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionService {
    private final QuestionService questionService;

    public QuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public QuestionVO create(QuestionVO question) {
        try {
            return questionService.create(question);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar pergunta " + e.getMessage());
        }
    }

    public QuestionVO update(QuestionVO question) {
        try {
            return questionService.update(question);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar pergunta " + e.getMessage());
        }
    }

    public QuestionVO findById(Integer id) {
        try {
            return questionService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar pergunta " + e.getMessage());
        }
    }

    public Boolean delete(Integer id) {
        try {
            return questionService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar pergunta " + e.getMessage());
        }
    }
}
