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
    public String forward(@PathVariable("path") String path) {
        return "portal/blog-" + path ;
    }

    /**
     * //此处要先插入空blog对象，获取blog的id，返回id给当前文章
     * @return
     */
    @RequestMapping("/edit")
    public ModelAndView edit(HttpSession session){
        Blog blog = new Blog();
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        blogService.saveBlog(blog);
        List<BlogType> blogTypes = blogService.listBlogType();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogid",blog.getId());
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
            modelAndView.addObject("msg","postblogsuccess");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //实际应该回到主页，并且主页刷出最新博客
        modelAndView.setViewName("redirect:/index.jsp");
        return modelAndView;
    }

    @RequestMapping("/showBlog")
    public ModelAndView showBlog(HttpServletRequest request,Integer blogid){
        ModelAndView modelAndView = new ModelAndView();
        if (blogid==null){
            String referer = request.getHeader("referer");//此处希望获得来源链接，到时返回到来时的页面
            modelAndView.addObject("msg","非法链接，无法打开文章");
            modelAndView.setViewName("redirect:/index.jsp");
            return modelAndView;
        }
        modelAndView.addObject("blogid",blogid);
        modelAndView.setViewName("redirect:/blog/show");
        return modelAndView;
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
        if(file==null||file.getOriginalFilename().equals("")){
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
                if (!f.getParentFile().exists()) {//找不到文件夹则新建文件夹
                    f.getParentFile().mkdirs();
                }
                file.transferTo(f);//上传文件
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

    @RequestMapping("/getBlog")
    public @ResponseBody Object getBlog(Integer blogid){
        Blog blog = blogService.getBlogById(blogid);
        User user = userService.getUserById(blog.getUserId());
        BlogShowVO blogShowVO = new BlogShowVO();
        BeanUtils.copyProperties(blog,blogShowVO);
        blogShowVO.setAuthorname(user.getUsername());
        return blogShowVO;
    }
}
