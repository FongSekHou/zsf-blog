package com.zsf.zsfblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @RequestMapping("/{path}")
    public String forward(@PathVariable("path") String path) {
        return "manage/manage-" + path ;
    }
}
