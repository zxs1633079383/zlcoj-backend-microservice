package com.zlc.springboot.testmyplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlc.springboot.testmyplus.model.User;
import com.zlc.springboot.testmyplus.service.UserService;
import com.zlc.springboot.testmyplus.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 16330
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-09-25 09:19:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




