package com.zlc.springboot.testmyplus.controller;

import com.zlc.springboot.testmyplus.mapper.UserMapper;
import com.zlc.springboot.testmyplus.model.User;
import com.zlc.springboot.testmyplus.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/get/id")
    public User getUserById(@RequestParam("userId") long userId) {
        return userMapper.selectById(userId);
//        return userService.getById(userId);
    }

}
