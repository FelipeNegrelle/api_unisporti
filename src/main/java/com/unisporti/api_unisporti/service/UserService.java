package com.unisporti.api_unisporti.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.unisporti.api_unisporti.model.User;
import com.unisporti.api_unisporti.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean validateUser(Long idUser, String cpf, String password) {
        final Optional<User> userOptional = userRepository.findById(idUser);

        if (userOptional.isEmpty()) {
            return false;
        }

        final User user = userOptional.get();

        if (!user.getCpf().equals(cpf)) {
            return false;
        }

        if (!user.getPassword().equals(password)) {
            return false;
        }

        if (!user.getActive()) {
            return false;
        }

        return true;
    }

    public User authenticate(String cpf, String password) {
        Optional<User> userOptional = userRepository.findByCpf(cpf);
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
}
