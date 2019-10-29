package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.Admin;
import com.zsf.zsfblog.po.Blog;
import com.zsf.zsfblog.po.BlogType;
import com.zsf.zsfblog.po.ManagePage;

import java.util.List;
import java.util.Map;

public interface ManageService {

    Admin getAdminByAdminnamePassword(String adminname,String password);

    ManagePage getUser(Integer pageNumber, int pageSize, String queryValue);

    int deleteUser(Integer uid,String username);

    int updateUserEnable(Integer uid,Integer enabled);

    ManagePage getArticle(Map<Object, Object> map);

    int deleteArticle(Integer[] blogid);

    ManagePage getColumn(Map<Object, Object> map);

    int deleteColumn(Integer[] typeid);

    int updateColumn(Integer typeid, String blogtypename);

    int addColumn(String blogtypename);

    int deleteUserBlog(Integer uid);

    List<BlogType> selectAllBlogType();

    List<Blog> selectAllBlog();

    int updateBlogType(Integer[] typeid);
}
