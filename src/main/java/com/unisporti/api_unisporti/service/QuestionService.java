package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.model.Question;
import com.unisporti.api_unisporti.repository.PollRepository;
import com.unisporti.api_unisporti.repository.QuestionRepository;
import com.unisporti.api_unisporti.repository.QuestionTypeRepository;
import com.unisporti.api_unisporti.vo.QuestionVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final PollRepository pollRepository;
    private final QuestionTypeRepository questionTypeRepository;

    public QuestionService(QuestionRepository questionRepository, PollRepository pollRepository, QuestionTypeRepository questionTypeRepository) {
        this.questionRepository = questionRepository;
        this.pollRepository = pollRepository;
        this.questionTypeRepository = questionTypeRepository;
    }

    public QuestionVO create(QuestionVO question) throws Exception {
        if (question != null) {
            final Question entity = new Question();

            entity.setPoll(pollRepository.findById(question.getIdPoll()).orElseThrow(() -> new MalformedRequestException("Enquete não encontrada.")));
//            entity.setQuestionType(questionTypeRepository.findById(question.getIdQuestionType()).orElseThrow(() -> new MalformedRequestException("Tipo de pergunta não encontrado.")));
            entity.setQuestion(question.getQuestion());
            entity.setRequired(question.getRequired());
            entity.setActive(question.getActive());

            final Question q = questionRepository.save(entity);

            return new QuestionVO(q.getIdQuestion(), q.getPoll().getIdPoll(), q.getQuestionType().getIdQuestionType(), q.getQuestion(), q.getRequired(), q.getActive());
        } else {
            throw new MalformedRequestException("Pergunta não pode ser nula.");
        }
    }

    public QuestionVO update(QuestionVO question) throws Exception {
        if (question != null) {
            final Question entity = questionRepository.findById(question.getIdQuestion()).orElseThrow(() -> new MalformedRequestException("Pergunta não encontrada."));

            entity.setPoll(pollRepository.findById(question.getIdPoll()).orElseThrow(() -> new MalformedRequestException("Enquete não encontrada.")));
            entity.setQuestionType(questionTypeRepository.findById(question.getIdQuestionType()).orElseThrow(() -> new MalformedRequestException("Tipo de pergunta não encontrado.")));
            entity.setQuestion(question.getQuestion());
            entity.setRequired(question.getRequired());
            entity.setActive(question.getActive());

            final Question q = questionRepository.save(entity);

            return new QuestionVO(q.getIdQuestion(), q.getPoll().getIdPoll(), q.getQuestionType().getIdQuestionType(), q.getQuestion(), q.getRequired(), q.getActive());
        } else {
            throw new MalformedRequestException("Pergunta não pode ser nula.");
        }
    }

    public List<QuestionVO> findAll() {
        final List<Question> questions = questionRepository.findAll();

        return questions.stream().map(q -> new QuestionVO(q.getIdQuestion(), q.getPoll().getIdPoll(), q.getQuestionType().getIdQuestionType(), q.getQuestion(), q.getRequired(), q.getActive())).toList();
    }

    public QuestionVO findById(Integer id) throws Exception {
        if (id != null) {
            final Question entity = questionRepository.findById(id).orElseThrow(() -> new MalformedRequestException("Pergunta não encontrada."));

            return new QuestionVO(entity.getIdQuestion(), entity.getPoll().getIdPoll(), entity.getQuestionType().getIdQuestionType(), entity.getQuestion(), entity.getRequired(), entity.getActive());
        } else {
            throw new MalformedRequestException("Pergunta não pode ser nula.");
        }
    }

    public boolean delete(Integer id) throws Exception {
        if (id != null) {
            final Question entity = questionRepository.findById(id).orElseThrow(() -> new MalformedRequestException("Pergunta não encontrada."));

            questionRepository.delete(entity);

            return true;
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }
}
