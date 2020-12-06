package com.zsf.zsfblog.interceptor;

import com.zsf.zsfblog.po.Admin;
import com.zsf.zsfblog.po.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Fangxihao
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Set<String> url = new HashSet<>();
        url.add("/user/notification");
        url.add("/user/personal");
        url.add("/blog/edit");
        url.add("/blog/publish");
        url.add("/blog/like");
        url.add("/blog/collect");
        url.add("/user/doLike");
        url.add("/user/doCollect");
        url.add("/user/cancelLike");
        url.add("/user/cancelCollect");
        url.add("/user/updateInfo");
        url.add("/blog/commitBlog");
        url.add("/blog/uploadImg");
        url.add("/blog/loadPublish");
        url.add("/blog/queryPublishBlogs");
        url.add("/blog/deleteBlog");
        url.add("/blog/loadLike");
        url.add("/blog/queryLikeBlogs");
        url.add("/blog/loadCollect");
        url.add("/blog/queryCollectBlogs");


        String path = request.getServletPath();
        if(url.contains(path)){
            User user = (User)request.getSession().getAttribute("user");
            if(user==null){
                response.sendRedirect(request.getContextPath()+"/user/login?flag=true");
                return false;
            }

        }else if(path.contains("/manage")){
            if(path.equals("/manage/login")||path.equals("/manage/doLogin")){
                return true;
            }
            Admin admin = (Admin)request.getSession().getAttribute("ADMIN");
            if(admin==null){
                response.sendRedirect(request.getContextPath()+"/manage/login");
                return false;
            }
        }
        return true;
    }
}
