package com.zsf.zsfblog.controller;

import com.zsf.zsfblog.po.User;
import com.zsf.zsfblog.po.UserPersonalVO;
import com.zsf.zsfblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{path}")
    public String forward(@PathVariable("path") String path) {
        return "portal/user-" + path ;
    }

    @RequestMapping("/doRegister")
    public ModelAndView doRegister(User user){
        ModelAndView modelAndView = new ModelAndView();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setEnabled(true);
            user.setRegtime(sdf.format(new Date()));
            userService.saveUser(user);
        }catch (Exception e){
            String message = e.getMessage().substring(e.getMessage().lastIndexOf(".") + 1);
            if (message.contains("username")) {
                modelAndView.addObject("usernameError","用户名已被使用");
            } else {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            modelAndView.setViewName("portal/user-login");
            return modelAndView;
        }
        modelAndView.addObject("msg","注册成功");
        modelAndView.setViewName("portal/user-login");
        return modelAndView;
    }


    @RequestMapping("/doLogin")
    public ModelAndView doLogin(String username, String password, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserByUsernamePassword(username,password);
        if(user!=null){
            request.getSession().setAttribute("user",user);
            modelAndView.addObject("msg","登录成功");
            modelAndView.setViewName("forward:/index.jsp");
        }else {
            modelAndView.addObject("msg","用户名或密码错误");
            modelAndView.setViewName("portal/user-login");
        }
        return modelAndView;
    }

    @RequestMapping("/doUpdate")
    public ModelAndView doUpdate(UserPersonalVO userVO,HttpSession session) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        MultipartFile icon = userVO.getIcon();
        if (icon != null) {
            String originalFilename = icon.getOriginalFilename();
            String perfixName = UUID.randomUUID().toString();
            String fileName = perfixName + originalFilename.substring(originalFilename.lastIndexOf("."));
            String path = "C:\\Users\\Fangxihao\\IdeaProjects\\zsf-Blog\\src\\main\\webapp\\icon\\" + fileName;
            File file = new File(path);
            if (!file.getParentFile().exists()) {//找不到文件夹则新建文件夹
                file.getParentFile().mkdirs();
            }
            icon.transferTo(file);
            User user = new User();
            BeanUtils.copyProperties(userVO, user);
            user.setIconpath("/icon/" + fileName);
            try {
                userService.updateUser(user);
                userService.getUserById(userVO.getId());
                session.setAttribute("user",user);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            modelAndView.addObject("msg", "修改成功");
        } else {
            modelAndView.addObject("msg", "请上传文件");
        }
        modelAndView.setViewName("portal/user-personal");
        return modelAndView;
    }
}
