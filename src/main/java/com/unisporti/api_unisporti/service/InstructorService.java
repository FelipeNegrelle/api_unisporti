package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.config.Role;
import com.unisporti.api_unisporti.config.Util;
import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Instructor;
import com.unisporti.api_unisporti.model.User;
import com.unisporti.api_unisporti.model.UserContext;
import com.unisporti.api_unisporti.repository.InstructorRepository;
import com.unisporti.api_unisporti.repository.ModalityRepository;
import com.unisporti.api_unisporti.repository.UserRepository;
import com.unisporti.api_unisporti.vo.InstructorAthleteVO;
import com.unisporti.api_unisporti.vo.InstructorVO;
import com.unisporti.api_unisporti.vo.ModalityVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;
    private final ModalityRepository modalityRepository;

    public InstructorService(InstructorRepository instructorRepository, UserRepository userRepository, ModalityRepository modalityRepository) {
        this.instructorRepository = instructorRepository;
        this.userRepository = userRepository;
        this.modalityRepository = modalityRepository;
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

                return new InstructorVO(i.getIdInstructor(), i.getUser().getIdUser(), User.getUserFullName(i.getUser()), i.getDegreeName(), i.getEducationalInstitution(), i.getStartDate(), i.getEndDate(), i.getActive());
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

                return new InstructorVO(i.getIdInstructor(), i.getUser().getIdUser(), User.getUserFullName(i.getUser()), i.getDegreeName(), i.getEducationalInstitution(), i.getStartDate(), i.getEndDate(), i.getActive());
            } else {
                throw new MalformedRequestException(Util.formatErrorMessage(errors));
            }
        } else {
            throw new MalformedRequestException("Instrutor não pode ser nulo.");
        }
    }

    public List<InstructorVO> findAll() {
        final List<Instructor> instructors = instructorRepository.findAll();

        return instructors.stream().map(i -> new InstructorVO(i.getIdInstructor(), i.getUser().getIdUser(), User.getUserFullName(i.getUser()), i.getDegreeName(), i.getEducationalInstitution(), i.getStartDate(), i.getEndDate(), i.getActive())).toList();
    }

    public InstructorVO findById(Integer id) {
        if (id != null) {
            final Instructor entity = instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("Instrutor não encontrado."));

            return new InstructorVO(entity.getIdInstructor(), entity.getUser().getIdUser(), User.getUserFullName(entity.getUser()), entity.getDegreeName(), entity.getEducationalInstitution(), entity.getStartDate(), entity.getEndDate(), entity.getActive());
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }

    public List<ModalityVO> getModalities() {
        final UserContext context = UserContext.getCurrentUser();

        if (Role.getHierarchyByRole(context.getRole()) < Role.ROLE_INSTRUCTOR.getHierarchy()) {
            throw new MalformedRequestException("Usuário não tem permissão para realizar esta operação.");
        } else {
            final Instructor instructor = instructorRepository.findByUserIdUser(context.getUserId()).orElseThrow(() -> new NotFoundException("Instrutor não encontrado."));

            return modalityRepository.findByInstructorIdInstructor(instructor.getIdInstructor()).stream().map(m -> new ModalityVO(m.getIdModality(), m.getInstructor().getIdInstructor(), m.getDescription(), m.getMaxParticipants(), m.getActive())).toList();
        }
    }

    public List<InstructorAthleteVO> getAthletes() {
        final UserContext context = UserContext.getCurrentUser();

        if (Role.getHierarchyByRole(context.getRole()) < Role.ROLE_INSTRUCTOR.getHierarchy()) {
            throw new MalformedRequestException("Usuário não tem permissão para realizar esta operação.");
        } else {
            final Instructor instructor = instructorRepository.findByUserIdUser(context.getUserId()).orElseThrow(() -> new NotFoundException("Instrutor não encontrado."));

            final List<Object[]> raw = instructorRepository.findAthletesByInstructor(instructor.getIdInstructor());

            return raw.stream()
                    .map(result -> new InstructorAthleteVO(
                            (Integer) result[0], // id_training
                            (Integer) result[1], // id_user
                            (String) result[2], // first_name
                            (String) result[3], // last_name
                            (String) result[4], // training_description
                            (String) result[5] // modality_description
                    ))
                    .toList();
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
