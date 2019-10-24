package com.zsf.zsfblog.service.impl;

import com.sun.xml.internal.ws.config.management.policy.ManagementPrefixMapper;
import com.zsf.zsfblog.mapper.ManageMapper;
import com.zsf.zsfblog.service.ManageService;
import com.zsf.zsfblog.po.Admin;
import org.springframework.beans.factory.annotation.Autowired;

public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;


    @Override
    public Admin getAdminByAdminnamePassword(String adminname, String password) {
        Admin admin = manageMapper.selectAdminByAdminnamePassword(adminname, password);
        return admin;
    }
}
