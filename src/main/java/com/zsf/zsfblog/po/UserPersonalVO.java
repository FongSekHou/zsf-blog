package com.zsf.zsfblog.po;

import org.springframework.web.multipart.MultipartFile;

public class UserPersonalVO extends User {

    private MultipartFile icon;

    public MultipartFile getIcon() {
        return icon;
    }

    public void setIcon(MultipartFile icon) {
        this.icon = icon;
    }

}
