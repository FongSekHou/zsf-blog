package com.zsf.zsfblog.controller;

import com.zsf.zsfblog.po.*;
import com.zsf.zsfblog.service.BlogService;
import com.zsf.zsfblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    /**
     * 在跳转页面前先判断是否是拦截器拦下来的，如果是就给提示信息，否则查询是否有最新消息
     * @param path
     * @param request
     * @param flag flag为true代表着是拦截器拦下来的请求
     * @return
     */
    @RequestMapping("/{path}")
    public ModelAndView forward(@PathVariable("path") String path, HttpServletRequest request,boolean flag){
        ModelAndView modelAndView = new ModelAndView();
       if("login".equals(path)&&flag==true){
           modelAndView.addObject("msg","请先登录");
           modelAndView.setViewName("portal/user-login");
           return modelAndView;
       }else {
           User user = (User)request.getSession().getAttribute("user");
           if(user!=null&&!"notification".equals(path)){
               Notice notice = userService.getLatestNotice(user.getId());
               if(notice!=null){
                   if(notice.getRead()==false){
                       //request.setAttribute("tipstyle","header-tip");用request也可以的
                       modelAndView.addObject("tipstyle","header-tip");
                   }
               }
           }
           modelAndView.setViewName("portal/user-" + path);
           return modelAndView;
       }
    }

    @RequestMapping("/doRegister")
    public ModelAndView doRegister(User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user/login");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setEnabled(true);
            user.setRegtime(sdf.format(new Date()));
            userService.saveUser(user);
        }catch (Exception e){
            String message = e.getMessage().substring(e.getMessage().lastIndexOf(".") + 1);
            if (message.contains("username")) {
                modelAndView.addObject("msg","用户名已被使用");
            } else {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return modelAndView;
        }
        modelAndView.addObject("msg","注册成功");
        return modelAndView;
    }


    @RequestMapping("/doLogin")
    public ModelAndView doLogin(String username, String password, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserByUsernamePassword(username,password);
        if(user!=null){
            if(user.getEnabled()){
                request.getSession().setAttribute("user",user);
                modelAndView.addObject("msg","登录成功");
                modelAndView.setViewName("redirect:/user/index");
            }else {
                modelAndView.addObject("msg","该账户处于禁用状态");
                modelAndView.setViewName("redirect:/user/login");
            }
        }else {
            modelAndView.addObject("msg","用户名或密码错误");
            modelAndView.setViewName("redirect:/user/login");
        }
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user!=null){
            session.invalidate();
        }
        return "redirect:/user/index";
    }

    @RequestMapping("/updateInfo")
    public ModelAndView updateInfo(HttpServletRequest request,User user,MultipartFile icon,String newPassword) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        if(newPassword!=null&&!"".equals(newPassword)){
            User realUser = userService.getUserByUsernamePassword(user.getUsername(),user.getPassword());
            if(realUser!=null){
                user.setPassword(newPassword);
            }else {
                modelAndView.addObject("msg","原密码错误");
                modelAndView.setViewName("redirect:/user/personal");
                return modelAndView;
            }
        }

        if(icon!=null&&!"".equals(icon.getOriginalFilename())){
            String originalFilename = icon.getOriginalFilename();
            String perfixName = UUID.randomUUID().toString();
            String fileName = perfixName + originalFilename.substring(originalFilename.lastIndexOf("."));
            String path = "C:\\Users\\Fangxihao\\IdeaProjects\\zsf-Blog\\src\\main\\webapp\\images\\icon\\" + fileName;
            File file = new File(path);
            //找不到文件夹则新建文件夹
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            icon.transferTo(file);
            user.setIconpath("/images/icon/" + fileName);
        }
        try {
            userService.updateUser(user);
            user = userService.getUserById(user.getId());
            request.getSession().setAttribute("user",user);
            request.setAttribute("iconpath",user.getIconpath());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        modelAndView.addObject("msg", "修改成功");
        modelAndView.setViewName("redirect:/user/personal");
        return modelAndView;
    }

    @RequestMapping("/loadIndex")
    public @ResponseBody Object loadIndex(HttpSession session){
        UserIndexVO userIndexVO = new UserIndexVO();
        User user = (User)session.getAttribute("user");
        if(user!=null){
            userIndexVO.setUser(user);
            userIndexVO.setPostblognum(blogService.getUserPostBlogNum(user.getId()));
            userIndexVO.setLikeblognum(blogService.getUserLikeBlogNum(user.getId()));
            userIndexVO.setCollectblognum(blogService.getUserCollectBlogNum(user.getId()));
        }
        List<BlogType> blogTypes;
        try{
            blogTypes = blogService.listBlogType();
            //加载主页时必定从第一页开始查
            PageBean pageBean = blogService.listBlogInPage(1,null,null);
            userIndexVO.setBlogTypes(blogTypes);
            userIndexVO.setPageBean(pageBean);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userIndexVO;
    }

    /**
     * 先增加博客赞数，再发送消息通知，再返回数据供页面展示
     * @param request
     * @param blogid
     * @return
     */

    @RequestMapping("/doLike")
    public @ResponseBody Object doLike(HttpServletRequest request,Integer blogid){
        User user = (User)request.getSession().getAttribute("user");
        Blog blog = blogService.getBlogById(blogid);
        blog.setLikenum(blog.getLikenum()+1);
        blogService.updateBlogLikenum(blogid,blog.getLikenum());

        Notice notice = new Notice();
        notice.setUserId(user.getId());
        notice.setBlogId(blogid);
        notice.setNoticetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userService.saveNotice(notice);

        return blog.getLikenum();
    }

    /**
     * 先增加博客收藏数，再向收藏表中插入数据保存关系，再发送消息通知，再返回数据供页面展示
     * @param request
     * @param blogid
     * @return
     */
    @RequestMapping("/doCollect")
    public @ResponseBody Object doCollect(HttpServletRequest request,Integer blogid){
        User user = (User)request.getSession().getAttribute("user");
        Blog blog = blogService.getBlogById(blogid);
        blog.setCollectnum(blog.getCollectnum()+1);
        blogService.updateBlogCollectnum(blogid,blog.getCollectnum());

        Collection collection = new Collection();
        collection.setBlogId(blogid);
        collection.setUserId(user.getId());
        userService.saveCollection(collection);

        Notice notice = new Notice();
        notice.setBlogId(blogid);
        notice.setUserId(user.getId());
        //collect中有blogid,但还是要设blogid，用于查询
        notice.setCollectionId(collection.getId());
        notice.setNoticetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        userService.saveNotice(notice);

        return blog.getCollectnum();
    }

    @RequestMapping("/cancelLike")
    public @ResponseBody Object cancelLike(HttpServletRequest request,Integer blogid){
        User user = (User)request.getSession().getAttribute("user");
        Blog blog = blogService.getBlogById(blogid);
        blog.setLikenum(blog.getLikenum()-1);
        blogService.updateBlogLikenum(blogid,blog.getLikenum());
        userService.removeNotice(user.getId(),blogid,null,null);
        return blog.getLikenum();
    }

    @RequestMapping("/cancelCollect")
    public @ResponseBody Object cancelCollect(HttpServletRequest request,Integer blogid){
        User user = (User)request.getSession().getAttribute("user");
        Blog blog = blogService.getBlogById(blogid);
        blog.setCollectnum(blog.getCollectnum()-1);
        blogService.updateBlogCollectnum(blogid,blog.getCollectnum());
        //已设置级联删除，那么通知表中有关收藏表的信息都会删除
        userService.removeCollection(user.getId(),blogid);
        return blog.getCollectnum();
    }

    @RequestMapping("/loadNotification")
    public @ResponseBody Object loadNotification(HttpServletRequest request){
        UserNotificationVO vo = new UserNotificationVO();
        User user = (User)request.getSession().getAttribute("user");
        vo.setUser(user);
        vo.setPostblognum(blogService.getUserPostBlogNum(user.getId()));
        vo.setLikeblognum(blogService.getUserLikeBlogNum(user.getId()));
        vo.setCollectblognum(blogService.getUserCollectBlogNum(user.getId()));
        PageBean pageBean = userService.listNoticeInPage(1, user.getId());
        vo.setPageBean(pageBean);
        return vo;
    }

    @RequestMapping("/queryNotices")
    public @ResponseBody Object queryNotices(Integer currentPage,HttpSession session){
        PageBean pageBean = userService.listNoticeInPage(currentPage,((User)session.getAttribute("user")).getId());
        return pageBean;
    }
}
