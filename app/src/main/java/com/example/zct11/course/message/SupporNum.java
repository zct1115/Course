package com.example.zct11.course.message;

/**
 * Created by zct11 on 2017/12/15.
 */

public class SupporNum {
    private int num;
    private boolean flag;

    public SupporNum(int num, boolean flag) {
        this.num = num;
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
