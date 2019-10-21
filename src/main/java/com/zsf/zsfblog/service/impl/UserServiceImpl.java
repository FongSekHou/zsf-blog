package com.zsf.zsfblog.service.impl;

import com.zsf.zsfblog.mapper.UserMapper;
import com.zsf.zsfblog.po.User;
import com.zsf.zsfblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserByUsernamePassword(String username,String password) {
        return userMapper.selectUserByUsernamePassword(username,password);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUserById(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
}
