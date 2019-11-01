package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.BlogType;

import java.util.List;

public interface BlogTypeMapper {

    List<BlogType> listBlogType();

    BlogType selectBlogTypeById(Integer id);
}
