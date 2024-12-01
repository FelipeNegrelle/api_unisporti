package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Training;
import com.unisporti.api_unisporti.model.User;
import com.unisporti.api_unisporti.model.TrainingUser;
import com.unisporti.api_unisporti.repository.TrainingRepository;
import com.unisporti.api_unisporti.repository.TrainingUserRepository;
import com.unisporti.api_unisporti.repository.UserRepository;
import com.unisporti.api_unisporti.vo.TrainingUserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingUserService {
    private final TrainingUserRepository trainingUserRepository;
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    public TrainingUserService(TrainingUserRepository trainingUserRepository, UserRepository userRepository, TrainingRepository trainingRepository) {
        this.trainingUserRepository = trainingUserRepository;
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    public TrainingUserVO create(TrainingUserVO trainingUserVO) throws Exception {
        if (trainingUserVO == null) {
            throw new MalformedRequestException("Dados inválidos.");
        }

        TrainingUser trainingUser = new TrainingUser();

        // Relacionamento com User
        User user = userRepository.findById(trainingUserVO.getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        trainingUser.setUser(user);

        // Relacionamento com Training
        Training training = trainingRepository.findById(trainingUserVO.getIdTraining())
                .orElseThrow(() -> new NotFoundException("Treino não encontrado."));
        trainingUser.setTraining(training);

        TrainingUser savedTrainingUser = trainingUserRepository.save(trainingUser);

        return new TrainingUserVO(
                savedTrainingUser.getIdTrainingUser(),
                savedTrainingUser.getTraining().getIdTraining(),
                savedTrainingUser.getUser().getIdUser()
        );
    }

    public TrainingUserVO findById(Integer id) throws Exception {
        TrainingUser trainingUser = trainingUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Associação de treino e usuário não encontrada."));

        return new TrainingUserVO(
                trainingUser.getIdTrainingUser(),
                trainingUser.getTraining().getIdTraining(),
                trainingUser.getUser().getIdUser()
        );
    }

    public List<TrainingUserVO> findAll() {
        return trainingUserRepository.findAll().stream()
                .map(trainingUser -> new TrainingUserVO(
                        trainingUser.getIdTrainingUser(),
                        trainingUser.getTraining().getIdTraining(),
                        trainingUser.getUser().getIdUser()))
                .toList();
    }

    public void delete(Integer id) throws Exception {
        TrainingUser trainingUser = trainingUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Associação de treino e usuário não encontrada."));

        trainingUserRepository.delete(trainingUser);
    }
}
