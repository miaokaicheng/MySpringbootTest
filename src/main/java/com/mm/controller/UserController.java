package com.mm.controller;

import com.mm.dto.User;
import com.mm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户类
 * @Author MKC
 * @Date 2021/12/28
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户ID获取用户信息
     * @param id id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Integer id) {
        return userService.getUserById(id);
    }

    /**
     * 保存用户
     * @param user 用户
     * @return 成功失败
     */
    @PostMapping("/save")
    public int saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
