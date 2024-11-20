package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.UserPlanService;
import com.unisporti.api_unisporti.vo.UserPlanVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/secure/admin/user-plan", "api/secure/manager/user-plan"})
public class UserPlanController {
    private final UserPlanService userPlanService;

    public UserPlanController(UserPlanService userPlanService) {
        this.userPlanService = userPlanService;
    }

    @PostMapping
    public UserPlanVO create(@RequestBody UserPlanVO userPlanVO) {
        try {
            return userPlanService.create(userPlanVO);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar associação: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public UserPlanVO findById(@PathVariable Integer id) {
        try {
            return userPlanService.findById(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao buscar associação: " + e.getMessage());
        }
    }

    @GetMapping
    public List<UserPlanVO> findAll() {
        return userPlanService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            userPlanService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar associação: " + e.getMessage());
        }
    }
}
