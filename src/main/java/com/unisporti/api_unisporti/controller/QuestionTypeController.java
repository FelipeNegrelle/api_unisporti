package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.QuestionTypeService;
import com.unisporti.api_unisporti.vo.QuestionTypeVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/question-type")
public class QuestionTypeController {
    private final QuestionTypeService questionTypeService;

    public QuestionTypeController(QuestionTypeService questionTypeService) {
        this.questionTypeService = questionTypeService;
    }

    public QuestionTypeVO create(QuestionTypeVO questionType) {
        try {
            return questionTypeService.create(questionType);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar tipo de pergunta " + e.getMessage());
        }
    }

    public QuestionTypeVO update(QuestionTypeVO questionType) {
        try {
            return questionTypeService.update(questionType);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar tipo de pergunta " + e.getMessage());
        }
    }

    public QuestionTypeVO findById(Integer id) {
        try {
            return questionTypeService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar tipo de pergunta " + e.getMessage());
        }
    }

    public Boolean delete(Integer id) {
        try {
            return questionTypeService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar tipo de pergunta " + e.getMessage());
        }
    }
}
