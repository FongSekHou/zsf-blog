package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.Admin;

public interface ManageService {

    Admin getAdminByAdminnamePassword(String adminname,String password);

}
