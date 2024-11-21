package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.config.Role;
import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Modality;
import com.unisporti.api_unisporti.model.UserContext;
import com.unisporti.api_unisporti.repository.InstructorRepository;
import com.unisporti.api_unisporti.repository.ModalityRepository;
import com.unisporti.api_unisporti.vo.ModalityVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ModalityService {
    private final ModalityRepository modalityRepository;
    private final InstructorRepository instructorRepository;

    public ModalityService(ModalityRepository modalityRepository, InstructorRepository instructorRepository) {
        this.modalityRepository = modalityRepository;
        this.instructorRepository = instructorRepository;
    }

    private Optional<Map<String, String>> validate(ModalityVO modality) {
        final Map<String, String> errors = new HashMap<>();

        if (Role.getHierarchyByRole(UserContext.getCurrentUser().getRole()) < Role.ROLE_INSTRUCTOR.getHierarchy()) {
            errors.put("role", "Usuário não tem permissão para realizar esta operação.");
        }

        if (modality.getDescription() != null && modality.getDescription().isBlank()) {
            errors.put("description", "Descrição não pode ser vazia.");
        }

        if (modality.getDescription() != null && modality.getDescription().length() > 50) {
            errors.put("description", "Descrição deve ter no máximo 50 caracteres.");
        }

        if (modality.getMaxParticipants() != null && modality.getMaxParticipants() > 32000) {
            errors.put("maxParticipants", "Número máximo de participantes deve ser menor que 32000.");
        }

        String description = null;
        if (modality.getIdModality() != null) {
            final Modality foundModality = modalityRepository.findByDescriptionAndIdModalityIsNot(modality.getDescription(), modality.getIdModality());

            if (foundModality != null) {
                description = foundModality.getDescription();
            }
        } else {
            final Modality foundModality = modalityRepository.findByDescription(modality.getDescription());

            if (foundModality != null) {
                description = foundModality.getDescription();
            }
        }

        if (modality.getDescription().equals(description)) {
            errors.put("description", "Modalidade com esta descrição já cadastrada.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public ModalityVO create(ModalityVO modality) throws Exception {
        if (modality != null) {
            final Map<String, String> errors = validate(modality).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final Modality entity = new Modality();
                entity.setInstructor(instructorRepository.findById(modality.getIdInstructor()).orElseThrow(() -> new NotFoundException("Instrutor não encontrado.")));
                entity.setDescription(modality.getDescription());
                entity.setMaxParticipants(modality.getMaxParticipants());
                entity.setActive(modality.getActive());

                final Modality m = modalityRepository.save(entity);

                return new ModalityVO(m.getIdModality(), m.getInstructor().getIdInstructor(), m.getDescription(), m.getMaxParticipants(), m.getActive());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Modalidade não pode ser nula.");
        }
    }

    public ModalityVO update(ModalityVO modality) throws Exception {
        if (modality != null) {
            final Map<String, String> errors = validate(modality).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final Modality entity = modalityRepository.findById(modality.getIdModality()).orElseThrow(() -> new NotFoundException("Modalidade não encontrada."));
                entity.setInstructor(instructorRepository.findById(modality.getIdInstructor()).orElseThrow(() -> new NotFoundException("Instrutor não encontrado.")));
                entity.setDescription(modality.getDescription());
                entity.setMaxParticipants(modality.getMaxParticipants());
                entity.setActive(modality.getActive());

                final Modality m = modalityRepository.save(entity);

                return new ModalityVO(m.getIdModality(), m.getInstructor().getIdInstructor(), m.getDescription(), m.getMaxParticipants(), m.getActive());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Modalidade não pode ser nula.");
        }
    }

    public List<ModalityVO> findAll() {
        final List<Modality> modalities = modalityRepository.findAll();

        return modalities.stream().map(m -> new ModalityVO(m.getIdModality(), m.getInstructor().getIdInstructor(), m.getDescription(), m.getMaxParticipants(), m.getActive())).toList();
    }

    public ModalityVO findById(Integer id) {
        if (id != null) {
            final Modality entity = modalityRepository.findById(id).orElseThrow(() -> new NotFoundException("Modalidade não encontrada."));

            return new ModalityVO(entity.getIdModality(), entity.getInstructor().getIdInstructor(), entity.getDescription(), entity.getMaxParticipants(), entity.getActive());
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) throws Exception {
        if (id != null) {
            final Modality entity = modalityRepository.findById(id).orElseThrow(() -> new NotFoundException("Modalidade não encontrada."));

            modalityRepository.delete(entity);

            return true;
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }
}
