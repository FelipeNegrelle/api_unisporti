package com.unisporti.api_unisporti.service;

import com.unisporti.api_unisporti.exception.MalformedRequestException;
import com.unisporti.api_unisporti.exception.NotFoundException;
import com.unisporti.api_unisporti.model.Plan;
import com.unisporti.api_unisporti.model.User;
import com.unisporti.api_unisporti.model.UserPlan;
import com.unisporti.api_unisporti.repository.PlanRepository;
import com.unisporti.api_unisporti.repository.UserPlanRepository;
import com.unisporti.api_unisporti.repository.UserRepository;
import com.unisporti.api_unisporti.vo.UserPlanVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlanService {
    private final UserPlanRepository userPlanRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    public UserPlanService(UserPlanRepository userPlanRepository, UserRepository userRepository, PlanRepository planRepository) {
        this.userPlanRepository = userPlanRepository;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }

    public UserPlanVO create(UserPlanVO userPlanVO) throws Exception {
        if (userPlanVO == null) {
            throw new MalformedRequestException("Dados inválidos.");
        }

        UserPlan userPlan = new UserPlan();

        // Relacionamento com User
        User user = userRepository.findById(userPlanVO.getIdUser())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        userPlan.setUser(user);

        // Relacionamento com Plan
        Plan plan = planRepository.findById(userPlanVO.getIdPlan())
                .orElseThrow(() -> new NotFoundException("Plano não encontrado."));
        userPlan.setPlan(plan);

        userPlan.setStartDate(userPlanVO.getStartDate());
        userPlan.setStatus(userPlanVO.getStatus());

        UserPlan savedUserPlan = userPlanRepository.save(userPlan);

        return new UserPlanVO(savedUserPlan.getIdUserPlan(),
                savedUserPlan.getUser().getIdUser(),
                savedUserPlan.getPlan().getIdPlan(),
                savedUserPlan.getStartDate(),
                savedUserPlan.getStatus());
    }

    public UserPlanVO findById(Integer id) throws Exception {
        UserPlan userPlan = userPlanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Associação de plano e usuário não encontrada."));

        return new UserPlanVO(userPlan.getIdUserPlan(),
                userPlan.getUser().getIdUser(),
                userPlan.getPlan().getIdPlan(),
                userPlan.getStartDate(),
                userPlan.getStatus());
    }

    public List<UserPlanVO> findAll() {
        return userPlanRepository.findAll().stream()
                .map(userPlan -> new UserPlanVO(
                        userPlan.getIdUserPlan(),
                        userPlan.getUser().getIdUser(),
                        userPlan.getPlan().getIdPlan(),
                        userPlan.getStartDate(),
                        userPlan.getStatus()))
                .toList();
    }

    public void delete(Integer id) throws Exception {
        UserPlan userPlan = userPlanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Associação de plano e usuário não encontrada."));

        userPlanRepository.delete(userPlan);
    }
}
