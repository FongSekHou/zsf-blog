package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ManageMapper {

    Admin selectAdminByAdminnamePassword(@Param("adminname") String adminname, @Param("password") String password);

    Integer selectAllUserCount(Map<Object,Object> map);

    List<?> selectUser(Map<Object,Object> map);

    int deleteUser(@Param("uid") Integer uid,@Param("username") String username);

    int updateUserEnabled(@Param("uid") Integer uid,@Param("enabled") Integer enabled);
}
