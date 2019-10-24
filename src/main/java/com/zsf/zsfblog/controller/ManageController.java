package com.zsf.zsfblog.controller;

import com.zsf.zsfblog.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {


    @Autowired
    private ManageService manageService;

    @RequestMapping("/{path}")
    public String forward(@PathVariable("path") String path) {
        return "manage/manage-" + path;
    }
    @RequestMapping("/doLogin")
    public String doLogin(String adminname,String password) {




        return "manage/manage-";
    }


}
