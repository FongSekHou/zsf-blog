package com.zsf.zsfblog.po;

public class BannedInfo {
    private Integer id;

    private String description;

    private String recoverytime;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRecoverytime() {
        return recoverytime;
    }

    public void setRecoverytime(String recoverytime) {
        this.recoverytime = recoverytime == null ? null : recoverytime.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}