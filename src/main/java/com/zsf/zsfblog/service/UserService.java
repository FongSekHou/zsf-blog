package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.User;

public interface UserService {
    void saveUser(User user);

    User getUserByUsernamePassword(String username,String password);

    int updateUser(User user);

    User getUserById(Integer id);
}
