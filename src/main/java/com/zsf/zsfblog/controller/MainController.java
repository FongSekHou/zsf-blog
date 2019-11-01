package com.zsf.zsfblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    /**
     * 前往主页的方法
     * @return
     */
    @RequestMapping("/")
    public String toIndex(){
        return "redirect:/user/index";
    }

}
