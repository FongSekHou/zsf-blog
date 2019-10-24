package com.zsf.zsfblog.service.impl;

import com.zsf.zsfblog.mapper.BlogImgMapper;
import com.zsf.zsfblog.mapper.BlogMapper;
import com.zsf.zsfblog.mapper.BlogTypeMapper;
import com.zsf.zsfblog.po.Blog;
import com.zsf.zsfblog.po.BlogImg;
import com.zsf.zsfblog.po.BlogType;
import com.zsf.zsfblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogImgMapper blogImgMapper;
    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Override
    public void saveBlog(Blog blog) {
        blogMapper.insertBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlogById(blog);
    }

    @Override
    public Blog getBlogById(Integer blogid) {
        return blogMapper.selectBlogById(blogid);
    }

    @Override
    public void saveBlogImg(BlogImg blogImg) {
        blogImgMapper.insertBlogImg(blogImg);
    }

    @Override
    public List<BlogType> listBlogType() {
        return blogTypeMapper.listBlogType();
    }
}
