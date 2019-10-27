package com.zsf.zsfblog.controller;

import com.zsf.zsfblog.po.Admin;
import com.zsf.zsfblog.po.ManagePage;
import com.zsf.zsfblog.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class ManageController {


    @Autowired
    private ManageService manageService;

    @RequestMapping("/{path}")
    public String forward(@PathVariable("path") String path) {
        return "manage/manage-" + path;
    }

    //管理员登录
    @RequestMapping("/doLogin")
    public String doLogin(String adminname, String password, HttpServletRequest request) {

        Admin admin = manageService.getAdminByAdminnamePassword(adminname, password);
        if(admin!=null){
            //判断账号是否禁用
            if(admin.getEnabled()){
                HttpSession session = request.getSession();
                session.setAttribute("ADMIN",admin);
                request.setAttribute("MANAGE_LOGIN_MESSAGE","登录成功！");
                return "redirect:/manage/article";
            }else{
                request.setAttribute("MANAGE_LOGIN_MESSAGE","当前账号处于禁用状态");
                return "manage/manage-login";
            }
        }
        request.setAttribute("MANAGE_LOGIN_MESSAGE","用户名或密码错误");
        return "manage/manage-login";
    }
    //管理员注销
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "manage/manage-login";
    }


    //用户查询
    @RequestMapping("/getUser")
    @ResponseBody
    public Object getUser(@RequestParam(value ="pageNumber",defaultValue = "1") Integer pageNumber,String queryValue) {
        //默认显示9个
        int pageSize=9;
        ManagePage page=manageService.getUser(pageNumber,pageSize,queryValue);
        return page;
    }
    //删除用户
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Object deleteUser(Integer uid,String username) {

        int result=manageService.deleteUser(uid,username);

        return result;
    }
    //修改用户状态
    @RequestMapping("/updateUser")
    @ResponseBody
    public Object updateUser(Integer uid,Integer enabled) {

        int result = manageService.updateUserEnable(uid, enabled);

        return result;
    }

    //文章显示管理
    //修改用户状态
    @RequestMapping("/getArticle")
    @ResponseBody
    public Object article(@RequestParam(value ="pageNumber",defaultValue = "1") Integer pageNumber,@RequestParam(value = "selectType",defaultValue = "true") boolean selectType,String queryValue ) {

        //默认8条
        Integer pageSize=8;
        Map<Object,Object> map=new HashMap<>();
        System.out.println("pagenumber"+pageNumber);

        System.out.println("queryValue"+queryValue);
        if(!StringUtils.isEmpty(queryValue)){
            map.put("queryValue",queryValue);
        }
        map.put("pageNumber",pageNumber);
        map.put("selectType", selectType);
        map.put("pageSize", pageSize);

        ManagePage page=manageService.getArticle(map);
        System.out.println(page);
        return page;
    }


    @RequestMapping("/deleteArticle")
    @ResponseBody
    public Object deleteArticle(@RequestParam("blogid") Integer[] blogid) {
        System.out.println(blogid.length+"长度");
        int result=manageService.deleteArticle(blogid);
        if(result!=blogid.length){
            return 0;
        }
        return result;
    }

}
