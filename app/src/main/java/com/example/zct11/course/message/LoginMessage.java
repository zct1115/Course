package com.example.zct11.course.message;

/**
 * Created by zct11 on 2017/12/13.
 */

public class LoginMessage {
    private boolean islogin;

    public LoginMessage(boolean islogin) {
        this.islogin = islogin;
    }

    public boolean isIslogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }
}
