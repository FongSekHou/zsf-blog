package com.zsf.zsfblog.po;

public class BlogType {
    private Integer id;

    private String blogtypename;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlogtypename() {
        return blogtypename;
    }

    public void setBlogtypename(String blogtypename) {
        this.blogtypename = blogtypename == null ? null : blogtypename.trim();
    }

}