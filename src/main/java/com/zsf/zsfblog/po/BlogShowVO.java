package com.zsf.zsfblog.po;

public class BlogShowVO extends Blog {

    private String authorname;

    private String blogtypename;

    /**
     * 记录当前用户与博客之间是否存在点赞关系的变量
     */
    private boolean likeRelation;

    private boolean collectRelation;

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getBlogtypename() {
        return blogtypename;
    }

    public void setBlogtypename(String blogtypename) {
        this.blogtypename = blogtypename;
    }

    public boolean isLikeRelation() {
        return likeRelation;
    }

    public void setLikeRelation(boolean likeRelation) {
        this.likeRelation = likeRelation;
    }

    public boolean isCollectRelation() {
        return collectRelation;
    }

    public void setCollectRelation(boolean collectRelation) {
        this.collectRelation = collectRelation;
    }
}
