package com.yupi.zlcojbackenduserservice.controller.inner;


import com.yupi.zlcojbackendjudemode.model.entity.User;
import com.yupi.zlcojbackendserviceclient.service.UserFeignClient;
import com.yupi.zlcojbackenduserservice.service.impl.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**UserInnerController该服务仅支持内部调用, 不能供前端调用
 *
 */
@RestController
@RequestMapping(("/inner"))
public class UserInnerController implements UserFeignClient {

    @Resource
    private UserService userService;


    @Override
    @GetMapping("/get/id")
    public User getById(@RequestParam("userId") long userId) {
        return userService.getById(userId);
    }

    @Override
    @GetMapping("/get/ids")
    public List<User> listByIds(@RequestBody Collection<Long> idList) {
        return userService.listByIds(idList);
    }
}
