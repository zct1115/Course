package com.example.zct11.course.message;

import java.io.File;

/**
 * Created by zct11 on 2017/12/15.
 */

public class InformationLogin {

    private File img;
    private String name;
    private String sex;

    public InformationLogin(File img, String name, String sex) {
        this.img = img;
        this.name = name;
        this.sex = sex;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
