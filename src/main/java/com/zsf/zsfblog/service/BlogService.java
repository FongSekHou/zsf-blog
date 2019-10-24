package com.zsf.zsfblog.service;

import com.zsf.zsfblog.po.Blog;
import com.zsf.zsfblog.po.BlogImg;
import com.zsf.zsfblog.po.BlogType;

import java.util.List;

public interface BlogService {

    void saveBlog(Blog blog);
    int updateBlog(Blog blog);

    Blog getBlogById(Integer blogid);

    void saveBlogImg(BlogImg blogImg);

    List<BlogType> listBlogType();
}
