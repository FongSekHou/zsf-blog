package com.zsf.zsfblog.service.impl;

import com.zsf.zsfblog.mapper.BlogMapper;
import com.zsf.zsfblog.mapper.ManageMapper;
import com.zsf.zsfblog.po.Admin;
import com.zsf.zsfblog.po.ManagePage;
import com.zsf.zsfblog.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public Admin getAdminByAdminnamePassword(String adminname, String password) {
        return manageMapper.selectAdminByAdminnamePassword(adminname, password);
    }

    @Override
    public ManagePage getUser(Integer pageNumber, int pageSize, String queryValue) {
        ManagePage page=new ManagePage(pageNumber,pageSize);
        Map<Object,Object> map=new HashMap<>();
        map.put("pageSize",pageSize);
        map.put("pageStart",page.getPageStart());
        map.put("queryValue", queryValue);
        page.setPageCount(manageMapper.selectAllUserCount(map));
        page.setData(manageMapper.selectUser(map));
        //显示分页按钮的个数
        page.getPageShow(6);
        return page;
    }

    @Override
    public int deleteUser(Integer uid,String username) {
        return manageMapper.deleteUser(uid,username);
    }

    @Override
    public int updateUserEnable(Integer uid, Integer enabled) {
        return manageMapper.updateUserEnabled(uid,enabled);
    }

    @Override
    public ManagePage getArticle(Map<Object, Object> map) {
        System.out.println((Integer) map.get("pageSize"));
        System.out.println("(Integer) map.get(pageNumber="+(Integer) map.get("pageNumber"));
        System.out.println("(Integer) map.get(pageSize="+(Integer) map.get("pageSize"));
        ManagePage managePage=new ManagePage((Integer) map.get("pageNumber"),(Integer) map.get("pageSize"));
        map.put("pageStart", managePage.getPageStart());
        System.out.println("map.get(pageStart)="+(Integer) map.get("pageStart"));
        //true为按title文章名查询，false为作者username查询
        if((Boolean) map.get("selectType")){
            System.out.println("title");
            int count = blogMapper.selectArticleCountByTitle(map);
            System.out.println("count"+count);
            managePage.setPageCount(count);
            managePage.setData(blogMapper.selectArticleByTitle(map));
        }else{
            System.out.println("username");
            int count = blogMapper.selectArticleCountByUsername(map);
            System.out.println("count"+count);
            managePage.setPageCount(count);
            managePage.setData(blogMapper.selectArticleByUsername(map));
            //显示分页按钮的个数
        }
        managePage.getPageShow(6);

        return managePage;
    }

    @Override
    public int deleteArticle(Integer[] blogid) {
        return blogMapper.deleteBlog(blogid);
    }

}
