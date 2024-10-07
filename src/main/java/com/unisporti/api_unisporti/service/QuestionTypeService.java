package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.model.QuestionType;
import com.unisporti.api_unisporti.repository.QuestionTypeRepository;
import com.unisporti.api_unisporti.vo.QuestionTypeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeService {
    private final QuestionTypeRepository questionTypeRepository;

    public QuestionTypeService(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    public QuestionTypeVO create(QuestionTypeVO questionType) throws Exception {
        if (questionType != null) {
            final QuestionType entity = new QuestionType();

            entity.setTypeName(questionType.getQuestionType());
            entity.setActive(questionType.getActive());

            final QuestionType qt = questionTypeRepository.save(entity);

            return new QuestionTypeVO(qt.getIdQuestionType(), qt.getTypeName(), qt.getActive());
        } else {
            throw new MalformedRequestException("Tipo de pergunta não pode ser nulo.");
        }
    }

    public QuestionTypeVO update(QuestionTypeVO questionType) throws Exception {
        if (questionType != null) {
            final QuestionType entity = questionTypeRepository.findById(questionType.getIdQuestionType()).orElseThrow(() -> new MalformedRequestException("Tipo de pergunta não encontrado."));

            entity.setTypeName(questionType.getQuestionType());
            entity.setActive(questionType.getActive());

            final QuestionType qt = questionTypeRepository.save(entity);

            return new QuestionTypeVO(qt.getIdQuestionType(), qt.getTypeName(), qt.getActive());
        } else {
            throw new MalformedRequestException("Tipo de pergunta não pode ser nulo.");
        }
    }

    public List<QuestionTypeVO> findAll() {
        final List<QuestionType> questionTypes = questionTypeRepository.findAll();

        return questionTypes.stream().map(qt -> new QuestionTypeVO(qt.getIdQuestionType(), qt.getTypeName(), qt.getActive())).toList();
    }

    public QuestionTypeVO findById(Integer id) throws Exception {
        if (id != null) {
            final QuestionType entity = questionTypeRepository.findById(id).orElseThrow(() -> new MalformedRequestException("Tipo de pergunta não encontrado."));

            return new QuestionTypeVO(entity.getIdQuestionType(), entity.getTypeName(), entity.getActive());
        } else {
            throw new MalformedRequestException("Tipo de pergunta não pode ser nulo.");
        }
    }

    public Boolean delete(Integer id) throws Exception {
        if (id != null) {
            final QuestionType entity = questionTypeRepository.findById(id).orElseThrow(() -> new MalformedRequestException("Tipo de pergunta não encontrado."));

            questionTypeRepository.delete(entity);

            return true;
        } else {
            throw new MalformedRequestException("Tipo de pergunta não pode ser nulo.");
        }
    }
}
