package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.Admin;
import org.apache.ibatis.annotations.Param;

public interface ManageMapper {

    Admin selectAdminByAdminnamePassword(@Param("adminname") String adminname, @Param("password") String password);
}
