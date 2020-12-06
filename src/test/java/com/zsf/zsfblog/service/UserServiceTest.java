package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-config.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser(){
        for(int i=0;i<200;i++){
            User user = new User();
            user.setUsername("testuser"+i);
            user.setPassword("123");
            user.setBirthday("1999-09-22");
            user.setGender(false);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setEnabled(true);
            user.setRegtime(sdf.format(new Date()));
            userService.saveUser(user);
        }
    }
}
