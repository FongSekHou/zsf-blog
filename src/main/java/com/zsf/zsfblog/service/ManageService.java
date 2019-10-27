package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.Admin;
import com.zsf.zsfblog.po.ManagePage;

import java.util.Map;

public interface ManageService {

    Admin getAdminByAdminnamePassword(String adminname,String password);

    ManagePage getUser(Integer pageNumber, int pageSize, String queryValue);

    int deleteUser(Integer uid,String username);

    int updateUserEnable(Integer uid,Integer enabled);

    ManagePage getArticle(Map<Object, Object> map);

    int deleteArticle(Integer[] blogid);
}
