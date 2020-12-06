package com.zsf.zsfblog.controller;

import com.zsf.zsfblog.po.*;
import com.zsf.zsfblog.service.BlogService;
import com.zsf.zsfblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @RequestMapping("/{path}")
    public String forward(@PathVariable("path") String path,HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if(user!=null){
            Notice notice = userService.getLatestNotice(user.getId());
            if(notice!=null){
                if(notice.getRead()==false){
                    request.setAttribute("tipstyle","header-tip");
                }
            }
        }
        return "portal/blog-" + path ;
    }

    /**
     * //此处要先插入空blog对象，获取blog的id，返回id给当前文章
     * @return
     */
    @RequestMapping("/edit")
    public ModelAndView edit(HttpSession session,Integer blogid){
        ModelAndView modelAndView = new ModelAndView();
        Blog blog;
        if(blogid!=null){
            blog = blogService.getBlogById(blogid);
        }else {
            blog = new Blog();
            User user = (User) session.getAttribute("user");
            blog.setUserId(user.getId());
            blogService.saveBlog(blog);
        }
        modelAndView.addObject("blog",blog);
        List<BlogType> blogTypes = blogService.listBlogType();
        modelAndView.addObject("blogtypes",blogTypes);
        modelAndView.setViewName("portal/blog-edit");
        return modelAndView;
    }

    @RequestMapping("/commitBlog")
    public ModelAndView commitBlog(HttpServletRequest request,Blog blog){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blog.setCompletetime(sdf.format(new Date()));
        ModelAndView modelAndView = new ModelAndView();
        try {
            blogService.updateBlog(blog);
            modelAndView.addObject("msg","发表博客成功");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        modelAndView.setViewName("redirect:/user/index");
        return modelAndView;
    }

    @RequestMapping("/showBlog")
    public ModelAndView showBlog(HttpServletRequest request,Integer blogid){
        ModelAndView modelAndView = new ModelAndView();
        if (blogid==null){
            //此处希望获得来源链接，到时返回到来时的页面
            String referer = request.getHeader("referer");
            modelAndView.addObject("msg","非法链接，无法打开文章");
            modelAndView.setViewName("redirect:/user/index");
            return modelAndView;
        }
        modelAndView.addObject("blogid",blogid);
        modelAndView.setViewName("redirect:/blog/show");
        return modelAndView;
    }

    @RequestMapping("/getBlog")
    public @ResponseBody Object getBlog(HttpSession session,Integer blogid){
        Blog blog = blogService.getBlogById(blogid);
        blog.setViewnum(blog.getViewnum()+1);
        blogService.updateBlogViewnum(blog.getId(),blog.getViewnum());
        User author = userService.getUserById(blog.getUserId());
        BlogShowVO blogShowVO = new BlogShowVO();
        BeanUtils.copyProperties(blog,blogShowVO);
        blogShowVO.setAuthorname(author.getUsername());
        User user = (User)session.getAttribute("user");
        if(user!=null){
            blogShowVO.setLikeRelation(userService.getLikeRelation(user.getId(),blogid));
            blogShowVO.setCollectRelation(userService.getCollectRelation(user.getId(),blogid));
        }
        return blogShowVO;
    }

    /**
     * 完成图片上传，返回在服务器中地址，以及将图片信息和博客信息将关联并存入数据库中
     * 必须要返回json对象而不是json格式的字符串，否则editor.md不能获取url属性
     * success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
     * message : "提示的信息，上传成功或上传失败及错误信息等。",
     * url     : "图片地址"        // 上传成功时才返回
     * @param file
     * @param blogid
     * @return
     */
    @RequestMapping("/uploadImg")
    public @ResponseBody Object uploadImg(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,Integer blogid,HttpServletRequest request) throws IOException {
        UploadImgResponseVO imgVO = new UploadImgResponseVO();
        if(file==null||"".equals(file.getOriginalFilename())){
            imgVO.setSuccess(0);
            imgVO.setMessage("上传图片为空");
            return imgVO;
        }else {
            try {
                String originalFilename = file.getOriginalFilename();
                String perfixName = UUID.randomUUID().toString();
                String fileName = perfixName + originalFilename.substring(originalFilename.lastIndexOf("."));
                String path = "C:\\Users\\Fangxihao\\IdeaProjects\\zsf-Blog\\src\\main\\webapp\\images\\blogimg\\" + fileName;
                File f = new File(path);
                //找不到文件夹则新建文件夹
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                //上传文件
                file.transferTo(f);
                String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
                String imgpath = basePath+"/images/blogimg/" + fileName;
                BlogImg blogImg = new BlogImg();
                blogImg.setImgpath(imgpath);
                blogImg.setBlogId(blogid);
                blogService.saveBlogImg(blogImg);

                imgVO.setSuccess(1);
                imgVO.setMessage("上传成功");

                imgVO.setUrl(imgpath);
            }catch (Exception e){
                e.printStackTrace();
                imgVO.setSuccess(0);
                imgVO.setMessage("出现异常");
            }
        }
        return imgVO;
    }

    @RequestMapping("/queryBlogs")
    public @ResponseBody Object queryBlogs(Integer currentPage,Integer blogtypeid,String condition){
        if(blogtypeid==null||blogtypeid<0){
            blogtypeid = null;
        }
        PageBean pageBean = blogService.listBlogInPage(currentPage,blogtypeid,condition);
        return pageBean;
    }

    @RequestMapping("/loadPublish")
    public @ResponseBody Object loadPublish(HttpSession session){
        UserIndexVO userIndexVO = new UserIndexVO();
        User user = (User)session.getAttribute("user");
        if(user!=null){
            userIndexVO.setUser(user);
            userIndexVO.setPostblognum(blogService.getUserPostBlogNum(user.getId()));
            userIndexVO.setLikeblognum(blogService.getUserLikeBlogNum(user.getId()));
            userIndexVO.setCollectblognum(blogService.getUserCollectBlogNum(user.getId()));
        }
        PageBean pageBean = blogService.listPublishBlogInPage(1,user.getId(),2,null);
        userIndexVO.setPageBean(pageBean);
        return userIndexVO;
    }

    @RequestMapping("/queryPublishBlogs")
    public @ResponseBody Object queryPublishBlogs(@RequestParam(defaultValue = "1")Integer currentPage,@RequestParam(defaultValue = "2") Integer blogstatus,HttpSession session,String condition){
        PageBean pageBean = blogService.listPublishBlogInPage(currentPage,((User)session.getAttribute("user")).getId(),blogstatus,condition);
        return pageBean;
    }

    @RequestMapping("/deleteBlog")
    public @ResponseBody Object deleteBlog(HttpSession session,Integer blogid,Integer blogstatus){
        Integer userPostNum;
        try {
            if(blogstatus==1||blogstatus==2){
                blogService.updateBlogStatus(blogid);
            }else {
                blogService.removeBlog(blogid);
            }
            User user = (User)session.getAttribute("user");
            userPostNum = blogService.getUserPostBlogNum(user.getId());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return userPostNum;
    }

    @RequestMapping("/loadLike")
    public @ResponseBody Object loadLike(HttpSession session){
        UserIndexVO userIndexVO = new UserIndexVO();
        User user = (User)session.getAttribute("user");
        if(user!=null){
            userIndexVO.setUser(user);
            userIndexVO.setPostblognum(blogService.getUserPostBlogNum(user.getId()));
            userIndexVO.setLikeblognum(blogService.getUserLikeBlogNum(user.getId()));
            userIndexVO.setCollectblognum(blogService.getUserCollectBlogNum(user.getId()));
        }
        List<BlogType> blogTypes = blogService.listBlogType();
        PageBean pageBean = blogService.listLikeBlogInPage(1,user.getId(),null,null);
        userIndexVO.setBlogTypes(blogTypes);
        userIndexVO.setPageBean(pageBean);
        return userIndexVO;
    }

    @RequestMapping("/queryLikeBlogs")
    public @ResponseBody Object queryLikeBlogs(@RequestParam(defaultValue = "1")Integer currentPage,HttpSession session,Integer blogtypeid,String condition){
        if(blogtypeid==null||blogtypeid<0){
            blogtypeid = null;
        }
        PageBean pageBean = blogService.listLikeBlogInPage(currentPage,((User)session.getAttribute("user")).getId(),blogtypeid,condition);
        return pageBean;
    }

    @RequestMapping("/loadCollect")
    public @ResponseBody Object loadCollect(HttpSession session){
        UserIndexVO userIndexVO = new UserIndexVO();
        User user = (User)session.getAttribute("user");
        if(user!=null){
            userIndexVO.setUser(user);
            userIndexVO.setPostblognum(blogService.getUserPostBlogNum(user.getId()));
            userIndexVO.setLikeblognum(blogService.getUserLikeBlogNum(user.getId()));
            userIndexVO.setCollectblognum(blogService.getUserCollectBlogNum(user.getId()));
        }
        List<BlogType> blogTypes = blogService.listBlogType();
        PageBean pageBean = blogService.listCollectBlogInPage(1,user.getId(),null,null);
        userIndexVO.setBlogTypes(blogTypes);
        userIndexVO.setPageBean(pageBean);
        return userIndexVO;
    }

    @RequestMapping("/queryCollectBlogs")
    public @ResponseBody Object queryCollectBlogs(@RequestParam(defaultValue = "1")Integer currentPage,HttpSession session,Integer blogtypeid,String condition){
        if(blogtypeid==null||blogtypeid<0){
            blogtypeid = null;
        }
        PageBean pageBean = blogService.listCollectBlogInPage(currentPage,((User)session.getAttribute("user")).getId(),blogtypeid,condition);
        return pageBean;
    }
}
