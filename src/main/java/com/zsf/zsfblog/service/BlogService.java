package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.Blog;
import com.zsf.zsfblog.po.BlogImg;
import com.zsf.zsfblog.po.BlogType;
import com.zsf.zsfblog.po.PageBean;

import java.util.List;

public interface BlogService {

    void saveBlog(Blog blog);
    int updateBlog(Blog blog);

    Blog getBlogById(Integer blogid);

    void saveBlogImg(BlogImg blogImg);

    List<BlogType> listBlogType();

    PageBean listBlogInPage(Integer currentPage,Integer blogtypeid,String condition);

    Integer getUserPostBlogNum(Integer userid);
    Integer getUserLikeBlogNum(Integer userid);
    Integer getUserCollectBlogNum(Integer userid);

    PageBean listPublishBlogInPage(Integer currentPage,Integer userid,Integer status,String condition);

    Integer updateBlogStatus(Integer id);

    Integer removeBlog(Integer id);

    Integer updateBlogViewnum(Integer blogid,Integer viewnum);

    Integer updateBlogLikenum(Integer blogid, Integer likenum);

    Integer updateBlogCollectnum(Integer blogid, Integer collectnum);

    PageBean listLikeBlogInPage(Integer currentPage, Integer userid,Integer blogtypeid, String condition);

    PageBean listCollectBlogInPage(Integer currentPage, Integer userid,Integer blogtypeid, String condition);
}
