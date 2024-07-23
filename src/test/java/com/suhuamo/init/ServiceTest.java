package com.suhuamo.init;

import com.suhuamo.init.pojo.User;
import com.suhuamo.init.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author suhuamo
 * @date 2024-03-21
 * @description
 */
@SpringBootTest
public class ServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        List<User> list = userService.list();
        System.out.println("list = " + list);
    }
}
