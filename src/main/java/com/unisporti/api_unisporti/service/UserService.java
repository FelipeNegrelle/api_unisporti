package com.unisporti.api_unisporti.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.User;
import com.unisporti.api_unisporti.repository.UserRepository;
import com.unisporti.api_unisporti.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<Map<String, String>> validate(UserVO user) {
        Map<String, String> errors = new HashMap<>();

        if (user.getCpf() == null || user.getCpf().isBlank()) {
            errors.put("cpf", "CPF não pode ser vazio.");
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            errors.put("email", "Email não pode ser vazio.");
        }

        if (user.getPhone() == null || user.getPhone().isBlank()) {
            errors.put("phone", "Telefone não pode ser vazio.");
        }

        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            errors.put("firstName", "Nome não pode ser vazio.");
        }

        if (user.getLastName() == null || user.getLastName().isBlank()) {
            errors.put("lastName", "Sobrenome não pode ser vazio.");
        }

        if (user.getBirthDate() == null) {
            errors.put("birthDate", "Data de nascimento não pode ser vazia.");
        }

        if (user.getRole() == null) {
            errors.put("role", "Role não pode ser vazia.");
        }

        return errors.isEmpty() ? Optional.empty() : Optional.of(errors);
    }

    public User authenticate(String cpf, String password) {
        final Optional<User> userOptional = userRepository.findByCpf(cpf);

        if (userOptional.isPresent()) {
            final User user = userOptional.get();

            if (user.getActive()) {
                if (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                    return user;
                }
            }
        }

        return null;
    }

    public UserVO create(UserVO userVO) {
        if (userVO != null) {
            final Map<String, String> errors = validate(userVO).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final User user = new User();
                user.setFirstName(userVO.getFirstName());
                user.setCpf(userVO.getCpf());
                user.setPassword(BCrypt.withDefaults().hashToString(12, userVO.getCpf().toCharArray()));
                user.setEmail(userVO.getEmail());
                user.setPhone(userVO.getPhone());
                user.setRole(userVO.getRole());
                user.setBirthDate(userVO.getBirthDate());
                user.setLastName(userVO.getLastName());
                user.setActive(true);

                final User entity = userRepository.save(user);

                return new UserVO(entity.getIdUser(), entity.getFirstName(), entity.getLastName(), entity.getCpf(), entity.getEmail(), entity.getPhone(), entity.getRole(), entity.getBirthDate(), entity.getActive());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Usuário não pode ser nulo.");
        }
    }

    public UserVO update(UserVO userVO) {
        if (userVO != null) {
            final Map<String, String> errors = validate(userVO).orElse(new HashMap<>());

            if (errors.isEmpty()) {
                final User entity = userRepository.findById(userVO.getIdUser()).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
                entity.setFirstName(userVO.getFirstName());
                entity.setCpf(userVO.getCpf());
                entity.setPassword(BCrypt.withDefaults().hashToString(12, userVO.getCpf().toCharArray()));
                entity.setEmail(userVO.getEmail());
                entity.setPhone(userVO.getPhone());
                entity.setRole(userVO.getRole());
                entity.setBirthDate(userVO.getBirthDate());
                entity.setLastName(userVO.getLastName());
                entity.setActive(true);

                final User updatedUser = userRepository.save(entity);

                return new UserVO(updatedUser.getIdUser(), updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getCpf(), updatedUser.getEmail(), updatedUser.getPhone(), updatedUser.getRole(), updatedUser.getBirthDate(), updatedUser.getActive());
            } else {
                throw new MalformedRequestException(List.of(errors));
            }
        } else {
            throw new MalformedRequestException("Usuário não pode ser nulo.");
        }
    }

    public List<UserVO> findAll() {
        final List<User> users = userRepository.findAll();

        return users.stream().map(u -> new UserVO(u.getIdUser(), u.getFirstName(), u.getLastName(), u.getCpf(), u.getEmail(), u.getPhone(), u.getRole(), u.getBirthDate(), u.getActive())).toList();
    }

    public UserVO findById(Integer id) {
        if (id != null) {
            final User entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

            return new UserVO(entity.getIdUser(), entity.getFirstName(), entity.getLastName(), entity.getCpf(), entity.getEmail(), entity.getPhone(), entity.getRole(), entity.getBirthDate(), entity.getActive());
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }

    public boolean delete(Integer id) {
        if (id != null) {
            final User entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

            userRepository.delete(entity);

            return true;
        } else {
            throw new MalformedRequestException("Id não pode ser nulo.");
        }
    }
}
