package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.config.Role;
import com.unisporti.api_unisporti.config.Util;
import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Instructor;
import com.unisporti.api_unisporti.model.User;
import com.unisporti.api_unisporti.model.UserContext;
import com.unisporti.api_unisporti.repository.InstructorRepository;
import com.unisporti.api_unisporti.repository.UserRepository;
import com.unisporti.api_unisporti.vo.InstructorVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;

    public InstructorService(InstructorRepository instructorRepository, UserRepository userRepository) {
        this.instructorRepository = instructorRepository;
        this.userRepository = userRepository;
    }

    private Optional<Map<String, String>> validate(InstructorVO instructor) {
        final UserContext context = UserContext.getCurrentUser();

        final Map<String, String> errors = new HashMap<>();

        if (Role.getHierarchyByRole(context.getRole()) < Role.ROLE_MANAGER.getHierarchy()) {
            errors.put("role", "Usuário não tem permissão para realizar esta operação.");
        }

        final User user = userRepository.findById(instructor.getIdUser()).orElse(null);
        if (user == null) {
            errors.put("idUser", "Usuário não encontrado.");
        } else {
            if (instructor.getIdInstructor() == null && instructorRepository.existsByUser(user)) {
                errors.put("idUser", "Usuário já cadastrado como instrutor.");
            }
        }

        if (instructor.getDegreeName().isBlank()) {
            errors.put("degreeName", "Graduação não pode ser vazia.");
        }

        if (instructor.getIdInstructor() != null && instructorRepository.existsByDegreeNameAndIdInstructor(instructor.getDegreeName(), instructor.getIdInstructor())) {
            errors.put("degreeName", "Graduação já cadastrada para o instrutor.");
        }

        if (instructor.getEducationalInstitution().isBlank()) {
            errors.put("educationalInstitution", "Instituição de ensino não pode ser vazia.");
        }

        if (instructor.getStartDate() == null) {
            errors.put("startDate", "Data de início não pode ser vazia.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public InstructorVO create(InstructorVO instructor) throws Exception {
        if (instructor != null) {
            final Map<String, String> errors = validate(instructor).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final Instructor entity = new Instructor();
                entity.setUser(userRepository.findById(instructor.getIdUser()).orElseThrow(() -> new NotFoundException("Usuário não encontrado.")));
                entity.setDegreeName(instructor.getDegreeName());
                entity.setEducationalInstitution(instructor.getEducationalInstitution());
                entity.setStartDate(instructor.getStartDate());
                entity.setEndDate(instructor.getEndDate());
                entity.setActive(instructor.getActive());

                final Instructor i = instructorRepository.save(entity);

                return new InstructorVO(i.getIdInstructor(), i.getUser().getIdUser(), i.getDegreeName(), i.getEducationalInstitution(), i.getStartDate(), i.getEndDate(), i.getActive());
            } else {
                throw new MalformedRequestException(Util.formatErrorMessage(errors));
            }
        } else {
            throw new MalformedRequestException("Instrutor não pode ser nulo.");
        }
    }

    public InstructorVO update(InstructorVO instructor) throws Exception {
        if (instructor != null) {
            final Map<String, String> errors = validate(instructor).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final Instructor entity = instructorRepository.findById(instructor.getIdInstructor()).orElseThrow(() -> new NotFoundException("Instrutor não encontrado."));
                entity.setUser(userRepository.findById(instructor.getIdUser()).orElseThrow(() -> new NotFoundException("Usuário não encontrado.")));
                entity.setDegreeName(instructor.getDegreeName());
                entity.setEducationalInstitution(instructor.getEducationalInstitution());
                entity.setStartDate(instructor.getStartDate());
                entity.setEndDate(instructor.getEndDate());
                entity.setActive(instructor.getActive());

                final Instructor i = instructorRepository.save(entity);

                return new InstructorVO(i.getIdInstructor(), i.getUser().getIdUser(), i.getDegreeName(), i.getEducationalInstitution(), i.getStartDate(), i.getEndDate(), i.getActive());
            } else {
                throw new MalformedRequestException(Util.formatErrorMessage(errors));
            }
        } else {
            throw new MalformedRequestException("Instrutor não pode ser nulo.");
        }
    }

    public List<InstructorVO> findAll() {
        final List<Instructor> instructors = instructorRepository.findAll();

        return instructors.stream().map(i -> new InstructorVO(i.getIdInstructor(), i.getUser().getIdUser(), i.getDegreeName(), i.getEducationalInstitution(), i.getStartDate(), i.getEndDate(), i.getActive())).toList();
    }

    public InstructorVO findById(Integer id) {
        if (id != null) {
            final Instructor entity = instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("Instrutor não encontrado."));

            return new InstructorVO(entity.getIdInstructor(), entity.getUser().getIdUser(), entity.getDegreeName(), entity.getEducationalInstitution(), entity.getStartDate(), entity.getEndDate(), entity.getActive());
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) throws Exception {
        if (id != null) {
            final Instructor entity = instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("Instrutor não encontrado."));

            instructorRepository.delete(entity);

            return true;
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }
}
