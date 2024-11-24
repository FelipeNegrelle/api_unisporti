package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Modality;
import com.unisporti.api_unisporti.model.Place;
import com.unisporti.api_unisporti.model.Training;
import com.unisporti.api_unisporti.repository.ModalityRepository;
import com.unisporti.api_unisporti.repository.PlaceRepository;
import com.unisporti.api_unisporti.repository.TrainingRepository;
import com.unisporti.api_unisporti.vo.TrainingVO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final ModalityRepository modalityRepository;
    private final PlaceRepository placeRepository;

    public TrainingService(TrainingRepository trainingRepository, ModalityRepository modalityRepository, PlaceRepository placeRepository) {
        this.trainingRepository = trainingRepository;
        this.modalityRepository = modalityRepository;
        this.placeRepository = placeRepository;
    }

    private Optional<Map<String, String>> validate(TrainingVO training) {
        Map<String, String> errors = new HashMap<>();

        if (training.getIdTraining() != null && trainingRepository.countByIdTrainingIsNot(training.getIdTraining()) > 0) {
            errors.put("id_training", "Já existe um treinamento com este ID.");
        }

        if (training.getIdModality() == null || modalityRepository.findById(training.getIdModality()).isEmpty()) {
            errors.put("id_modality", "Modalidade não encontrada.");
        }

        if (training.getIdPlace() == null || placeRepository.findById(training.getIdPlace()).isEmpty()) {
            errors.put("id_place", "Local não encontrado.");
        }

        if (training.getDescription() == null || training.getDescription().isBlank()) {
            errors.put("description", "Descrição não pode ser vazia.");
        }

        if (training.getWeekDay() == null) {
            errors.put("week_day", "Dia da semana não pode ser nulo.");
        }

        if (training.getWeekDay() != null && Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7}).noneMatch(i -> i == training.getWeekDay())) {
            errors.put("week_day", "Dia da semana inválido.");
        }

        if (training.getStartHour() != null && (training.getStartHour() < 0 || training.getStartHour() > 24)) {
            errors.put("start_hour", "Hora de início inválida.");
        }

        if (training.getEndHour() != null && (training.getEndHour() < 0 || training.getEndHour() > 24)) {
            errors.put("end_hour", "Hora de término inválida.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public TrainingVO create(TrainingVO vo) {
        if (vo != null) {
            Map<String, String> errors = validate(vo).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                Modality modality = modalityRepository.findById(vo.getIdModality()).orElseThrow(() -> new NotFoundException("Modalidade não encontrada."));

                Place place = placeRepository.findById(vo.getIdPlace()).orElseThrow(() -> new NotFoundException("Local não encontrado."));

                Training training = new Training();
                training.setModality(modality);
                training.setPlace(place);
                training.setDescription(vo.getDescription());
                training.setWeekDay(vo.getWeekDay());
                training.setStartHour(vo.getStartHour());
                training.setEndHour(vo.getEndHour());
                training.setActive(vo.getActive());

                Training savedTraining = trainingRepository.save(training);
                return new TrainingVO(savedTraining.getIdTraining(), savedTraining.getModality().getIdModality(), savedTraining.getPlace().getIdPlace(), savedTraining.getDescription(), savedTraining.getWeekDay(), savedTraining.getStartHour(), savedTraining.getEndHour(), savedTraining.getActive());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Treinamento não pode ser nulo.");
        }
    }

    public List<TrainingVO> findAll() {
        return trainingRepository.findAll().stream().map(training -> new TrainingVO(training.getIdTraining(), training.getModality().getIdModality(), training.getPlace().getIdPlace(), training.getDescription(), training.getWeekDay(), training.getStartHour(), training.getEndHour(), training.getActive())).toList();
    }

    public TrainingVO findById(Integer id) {
        Training training = trainingRepository.findById(id).orElseThrow(() -> new NotFoundException("Treinamento não encontrado."));
        return new TrainingVO(training.getIdTraining(), training.getModality().getIdModality(), training.getPlace().getIdPlace(), training.getDescription(), training.getWeekDay(), training.getStartHour(), training.getEndHour(), training.getActive());
    }

    public TrainingVO update(TrainingVO vo) {
        if (vo != null) {
            final Map<String, String> errors = validate(vo).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                Modality modality = modalityRepository.findById(vo.getIdModality()).orElseThrow(() -> new NotFoundException("Modalidade não encontrada."));

                Place place = placeRepository.findById(vo.getIdPlace()).orElseThrow(() -> new NotFoundException("Local não encontrado."));

                Training training = trainingRepository.findById(vo.getIdTraining()).orElseThrow(() -> new NotFoundException("Treinamento não encontrado."));
                training.setModality(modality);
                training.setPlace(place);
                training.setDescription(vo.getDescription());
                training.setWeekDay(vo.getWeekDay());
                training.setStartHour(vo.getStartHour());
                training.setEndHour(vo.getEndHour());
                training.setActive(vo.getActive());

                Training updatedTraining = trainingRepository.save(training);
                return new TrainingVO(updatedTraining.getIdTraining(), updatedTraining.getModality().getIdModality(), updatedTraining.getPlace().getIdPlace(), updatedTraining.getDescription(), updatedTraining.getWeekDay(), updatedTraining.getStartHour(), updatedTraining.getEndHour(), updatedTraining.getActive());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Treinamento não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) {
        if (id != null) {
            Training training = trainingRepository.findById(id).orElseThrow(() -> new NotFoundException("Treinamento não encontrado."));
            trainingRepository.delete(training);

            return true;
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }
}
