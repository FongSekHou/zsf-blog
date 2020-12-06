package com.zsf.zsfblog.service.impl;

import com.zsf.zsfblog.mapper.BlogImgMapper;
import com.zsf.zsfblog.mapper.BlogMapper;
import com.zsf.zsfblog.mapper.BlogTypeMapper;
import com.zsf.zsfblog.mapper.UserMapper;
import com.zsf.zsfblog.po.*;
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
    @Autowired
    private UserMapper userMapper;

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

    @Override
    public PageBean listBlogInPage(Integer currentPage,Integer blogtypeid,String condition) {
        PageBean pageBean = new PageBean(currentPage);
        pageBean.setTotalRecords(blogMapper.countBlogs(blogtypeid,condition));
        List<BlogShowVO> blogs = blogMapper.selectBlogHaveLimit(pageBean.getStartIndex(), pageBean.getPageLines(),blogtypeid,condition);
        for(BlogShowVO blogShowVO:blogs){
            blogShowVO.setAuthorname(userMapper.selectUserById(blogShowVO.getUserId()).getUsername());
            blogShowVO.setBlogtypename(blogTypeMapper.selectBlogTypeById(blogShowVO.getBlogtypeId()).getBlogtypename());
        }
        pageBean.setEntitys(blogs);
        return pageBean;
    }

    @Override
    public Integer getUserPostBlogNum(Integer userid) {
        return blogMapper.selectUserPostBlogNum(userid);
    }

    @Override
    public Integer getUserLikeBlogNum(Integer userid) {
        return blogMapper.selectUserLikeBlogNum(userid,null,null);
    }

    @Override
    public Integer getUserCollectBlogNum(Integer userid) {
        return blogMapper.selectUserCollectBlogNum(userid,null,null);
    }

    @Override
    public PageBean listPublishBlogInPage(Integer currentPage, Integer userid, Integer status,String condition) {
        PageBean pageBean = new PageBean(currentPage);
        pageBean.setTotalRecords(blogMapper.countPublishBlogs(userid,status,condition));
        List<BlogShowVO> blogs = blogMapper.selectPublishBlogHaveLimit(pageBean.getStartIndex(), pageBean.getPageLines(), userid, status,condition);
        for(BlogShowVO blogShowVO:blogs){
            blogShowVO.setAuthorname(userMapper.selectUserById(blogShowVO.getUserId()).getUsername());
            blogShowVO.setBlogtypename(blogTypeMapper.selectBlogTypeById(blogShowVO.getBlogtypeId()).getBlogtypename());
        }
        pageBean.setEntitys(blogs);
        return pageBean;
    }

    @Override
    public Integer updateBlogStatus(Integer id) {
        return blogMapper.updateBlogStatus(id);
    }

    @Override
    public Integer removeBlog(Integer id) {
        return blogImgMapper.deleteBlogImgByBlogId(id)+blogMapper.deleteBlogById(id);
    }

    @Override
    public Integer updateBlogViewnum(Integer blogid,Integer viewnum) {
        return blogMapper.updateBlogViewnum(blogid,viewnum);
    }

    @Override
    public Integer updateBlogLikenum(Integer blogid, Integer likenum) {
        return blogMapper.updateBlogLikenum(blogid,likenum);
    }

    @Override
    public Integer updateBlogCollectnum(Integer blogid, Integer collectnum) {
        return blogMapper.updateBlogCollectnum(blogid,collectnum);
    }

    @Override
    public PageBean listLikeBlogInPage(Integer currentPage, Integer userid,Integer blogtypeid, String condition) {
        PageBean pageBean = new PageBean(currentPage);
        pageBean.setTotalRecords(blogMapper.selectUserLikeBlogNum(userid,blogtypeid,condition));
        List<BlogShowVO> blogs = blogMapper.selectLikeBlogHaveLimit(pageBean.getStartIndex(), pageBean.getPageLines(), userid,blogtypeid,condition);
        for(BlogShowVO blogShowVO:blogs){
            blogShowVO.setAuthorname(userMapper.selectUserById(blogShowVO.getUserId()).getUsername());
            blogShowVO.setBlogtypename(blogTypeMapper.selectBlogTypeById(blogShowVO.getBlogtypeId()).getBlogtypename());
        }
        pageBean.setEntitys(blogs);
        return pageBean;
    }

    @Override
    public PageBean listCollectBlogInPage(Integer currentPage, Integer userid, Integer blogtypeid, String condition) {
        PageBean pageBean = new PageBean(currentPage);
        pageBean.setTotalRecords(blogMapper.selectUserCollectBlogNum(userid,blogtypeid,condition));
        List<BlogShowVO> blogs = blogMapper.selectCollectBlogHaveLimit(pageBean.getStartIndex(), pageBean.getPageLines(), userid,blogtypeid,condition);
        for(BlogShowVO blogShowVO:blogs){
            blogShowVO.setAuthorname(userMapper.selectUserById(blogShowVO.getUserId()).getUsername());
            blogShowVO.setBlogtypename(blogTypeMapper.selectBlogTypeById(blogShowVO.getBlogtypeId()).getBlogtypename());
        }
        pageBean.setEntitys(blogs);
        return pageBean;
    }


}
