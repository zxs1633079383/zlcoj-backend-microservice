package com.yupi.zlcojbackenduserservice;

import com.yupi.zlcojbackendjudemode.model.entity.User;
import com.yupi.zlcojbackenduserservice.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ZlcOjBackendUserServiceApplicationTests {

	@Resource
	private UserService userService;

	@Test
	void contextLoads() {
		User user = userService.getById(2L);
		System.out.println("用户为:" + user.toString());
	}

}
