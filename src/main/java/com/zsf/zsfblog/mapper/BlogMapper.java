package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.Blog;
import com.zsf.zsfblog.po.BlogType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    Integer selectArticleCountByTitle(Map<Object, Object> map);

    List<?> selectArticleByTitle(Map<Object, Object> map);

    Integer selectArticleCountByUsername(Map<Object, Object> map);

    List<?> selectArticleByUsername(Map<Object, Object> map);

    int deleteBlog(Integer[] blogid);

    Integer selectColumnCount(Map<Object, Object> map);

    List<BlogType> selectCoulumn(Map<Object, Object> map);

    int deleteColumn(Integer[] typeid);

    int updateColumn(@Param("typeid") Integer typeid, @Param("blogtypename") String blogtypename);

    int addColumn(String blogtypename);

    int deleteUserBlog(Integer uid);

    List<Blog> selectAllBlog();

    List<BlogType> selectBlogType();

    int updateBlogType(Integer[] typeid);
}
