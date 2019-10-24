package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.Blog;

public interface BlogMapper {

    int insertBlog(Blog blog);

    int updateBlogById(Blog blog);

    Blog selectBlogById(Integer blogid);
}
