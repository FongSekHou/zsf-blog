package com.zsf.zsfblog.po;

import java.util.List;

public class UserIndexVO {

    private User user;
    private List<BlogType> blogTypes;
    private PageBean pageBean;
    private int postblognum;
    private int likeblognum;
    private int collectblognum;

    public void setBlogTypes(List<BlogType> blogTypes) {
        this.blogTypes = blogTypes;
    }

    public List<BlogType> getBlogTypes() {
        return blogTypes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public int getPostblognum() {
        return postblognum;
    }

    public void setPostblognum(int postblognum) {
        this.postblognum = postblognum;
    }

    public int getLikeblognum() {
        return likeblognum;
    }

    public void setLikeblognum(int likeblognum) {
        this.likeblognum = likeblognum;
    }

    public int getCollectblognum() {
        return collectblognum;
    }

    public void setCollectblognum(int collectblognum) {
        this.collectblognum = collectblognum;
    }
}
