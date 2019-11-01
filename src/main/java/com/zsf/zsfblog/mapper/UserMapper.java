package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.Notice;
import com.zsf.zsfblog.po.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int insertUser(User user);

    User selectUserById(int id);

    User selectUserByUsernamePassword(@Param("username") String username,@Param("password") String password);

    int updateUserById(User user);

}
