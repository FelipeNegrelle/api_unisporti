package com.unisporti.api_unisporti.controller;

import com.unisporti.api_unisporti.exception.ServerException;
import com.unisporti.api_unisporti.service.UserService;
import com.unisporti.api_unisporti.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/secure/admin/user", "/api/secure/manager/user/", "/aopi/secure/instructor/user"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserVO> listUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserVO findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserVO create(@RequestBody UserVO user) {
        try {
            return userService.create(user);
        } catch (Exception e) {
            throw new ServerException("Erro ao criar usuário " + e.getMessage());
        }
    }

    @PutMapping
    public UserVO update(@RequestBody UserVO user) {
        try {
            return userService.update(user);
        } catch (Exception e) {
            throw new ServerException("Erro ao atualizar usuário " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        try {
            return userService.delete(id);
        } catch (Exception e) {
            throw new ServerException("Erro ao deletar usuário " + e.getMessage());
        }
    }
}
