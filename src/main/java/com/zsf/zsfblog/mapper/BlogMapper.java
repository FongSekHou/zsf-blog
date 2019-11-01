package com.zsf.zsfblog.mapper;

import com.zsf.zsfblog.po.Blog;
import com.zsf.zsfblog.po.BlogShowVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {

    int insertBlog(Blog blog);

    int updateBlogById(Blog blog);

    Blog selectBlogById(Integer blogid);

    Integer countBlogs(@Param("blogtypeid") Integer blogtypeid,@Param("condition") String condition);

    List<BlogShowVO> selectBlogHaveLimit(@Param("offset") Integer offset, @Param("number") Integer number,@Param("blogtypeid") Integer blogtypeid,@Param("condition") String condition);

    Integer selectUserPostBlogNum(Integer userid);

    Integer selectUserLikeBlogNum(@Param("userid") Integer userid,@Param("blogtypeid") Integer blogtypeid,@Param("condition") String condition);

    Integer selectUserCollectBlogNum(@Param("userid") Integer userid,@Param("blogtypeid") Integer blogtypeid,@Param("condition") String condition);

    Integer countPublishBlogs(@Param("userid")Integer userid,@Param("status")Integer status,@Param("condition") String condition);

    List<BlogShowVO> selectPublishBlogHaveLimit(@Param("offset") Integer offset, @Param("number") Integer number,@Param("userid") Integer userid,@Param("status")Integer status,@Param("condition") String condition);

    Integer deleteBlogById(Integer id);

    Integer updateBlogStatus(Integer id);

    Integer updateBlogViewnum(@Param("id") Integer id, @Param("viewnum") Integer viewnum);

    Integer updateBlogLikenum(@Param("id") Integer id, @Param("likenum") Integer likenum);

    Integer updateBlogCollectnum(@Param("id")Integer id,@Param("collectnum") Integer collectnum);

    List<BlogShowVO> selectLikeBlogHaveLimit(@Param("offset")Integer startIndex,@Param("number") Integer pageLines, @Param("userid")Integer userid,@Param("blogtypeid") Integer blogtypeid, @Param("condition")String condition);

    List<BlogShowVO> selectCollectBlogHaveLimit(@Param("offset")Integer startIndex,@Param("number") Integer pageLines, @Param("userid")Integer userid,@Param("blogtypeid") Integer blogtypeid, @Param("condition")String condition);
}
