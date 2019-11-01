package com.zsf.zsfblog.po;

public class ArticleVO extends Blog{

    private String username;

    private String blogtypename;

    private boolean typestatus;

    public boolean isTypestatus() {
        return typestatus;
    }

    public void setTypestatus(boolean typestatus) {
        this.typestatus = typestatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBlogtypename() {
        return blogtypename;
    }

    public void setBlogtypename(String blogtypename) {
        this.blogtypename = blogtypename;
    }
}
