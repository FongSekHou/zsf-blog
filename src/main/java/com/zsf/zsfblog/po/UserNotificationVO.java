package com.zsf.zsfblog.po;

/**
 * 整个消息通知页面的vo
 */
public class UserNotificationVO {

    private User user;
    private PageBean pageBean;
    private int postblognum;
    private int likeblognum;
    private int collectblognum;

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
