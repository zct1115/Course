package com.example.zct11.course.message;

import com.example.zct11.course.R;
import com.example.zct11.course.bean.CardData;
import com.example.zct11.course.bean.Comment;
import com.ramotion.expandingcollection.ECCardData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/12/12.
 */

public class Dataset {
    private List<ECCardData> dataset;

    public Dataset() {
        dataset = new ArrayList<>(5);

        CardData item5 = new CardData();
        item5.setMainBackgroundResource(R.mipmap.android);
        item5.setHeadBackgroundResource(R.mipmap.android);
        item5.setHeadTitle("Android 工程师");
        item5.setPersonMessage("百度公司Android资深工程师");
        item5.setPersonName("任玉刚");
        item5.setPersonPictureResource(R.mipmap.renyugang);
        item5.setListItems(prepareCommentsArray());
        dataset.add(item5);

        CardData item4 = new CardData();
        item4.setMainBackgroundResource(R.mipmap.html);
        item4.setHeadBackgroundResource(R.mipmap.html);
        item4.setHeadTitle("前端工程师");
        item4.setPersonMessage("百度前端工程师");
        item4.setPersonName("张立理");
        item4.setPersonPictureResource(R.mipmap.zhanglili);
        item4.setListItems(prepareCommentsArray());
        dataset.add(item4);

        CardData item3 = new CardData();
        item3.setMainBackgroundResource(R.mipmap.all);
        item3.setHeadBackgroundResource(R.mipmap.all);
        item3.setHeadTitle("大数据与云计算工程师");
        item3.setPersonMessage("大数据与云计算工程师");
        item3.setPersonName("曹永军");
        item3.setPersonPictureResource(R.mipmap.caoyongjun);
        item3.setListItems(prepareCommentsArray());
        dataset.add(item3);

        CardData item2 = new CardData();
        item2.setMainBackgroundResource(R.mipmap.robot);
        item2.setHeadBackgroundResource(R.mipmap.robot);
        item2.setHeadTitle("人工智能工程师");
        item2.setPersonName("黄士杰");
        item2.setPersonMessage("人工智能工程师");
        item2.setListItems(prepareCommentsArray());
        item2.setPersonPictureResource(R.mipmap.huangshijie);
        dataset.add(item2);

        CardData item1 = new CardData();
        item1.setMainBackgroundResource(R.mipmap.web);
        item1.setHeadBackgroundResource(R.mipmap.web);
        item1.setHeadTitle("后端工程师");
        item1.setPersonMessage("百度 高级后端工程师");
        item1.setPersonName("李嘉璇");
        item1.setPersonPictureResource(R.mipmap.lijiaxuan);
        item1.setListItems(prepareCommentsArray());
        dataset.add(item1);
    }

    public List<ECCardData> getDataset() {
        return dataset;
    }

    private List<Comment> prepareCommentsArray() {
        Random random = new Random();
        List<Comment> comments = new ArrayList<>();
        comments.addAll(Arrays.asList(
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", ""),
                new Comment(R.mipmap.renyugang, "", "", "")));
        return comments.subList(0, 0);
    }
}
