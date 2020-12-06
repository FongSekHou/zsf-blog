package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.BlogImg;

import java.util.List;

public interface BlogImgMapper {

    int insertBlogImg(BlogImg blogImg);

    int deleteBlogImgByBlogId(Integer blogid);

}
