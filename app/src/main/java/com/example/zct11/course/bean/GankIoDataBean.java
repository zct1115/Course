package com.example.zct11.course.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zct11 on 2017/11/1.
 */

public class GankIoDataBean implements Serializable {


    /**
     * error : false
     * results : [{"_id":"59f6a9ef421aa90fef2034dd","createdAt":"2017-10-30T12:26:23.779Z","desc":"Android Studio3.0填坑指南","images":["http://img.gank.io/64396106-8bf6-4038-9c14-0e309835296b"],"publishedAt":"2017-10-31T12:25:55.217Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/b45d68c98828","used":true,"who":"阿韦"},{"_id":"59f7d6bc421aa90fe50c01b5","createdAt":"2017-10-31T09:49:48.830Z","desc":"Android 仿微信微博的展开全文功能","publishedAt":"2017-10-31T12:25:55.217Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzIwMzYwMTk1NA==&mid=2247487876&idx=1&sn=7406cceabe26925307dae53a881c41aa","used":true,"who":"陈宇明"},{"_id":"59f7dd59421aa90fe2f02c09","createdAt":"2017-10-31T10:18:01.749Z","desc":"拿到了新浪微博、小米和京东的 offer，应该如何做选择？","publishedAt":"2017-10-31T12:25:55.217Z","source":"web","type":"Android","url":"https://mp.weixin.qq.com/s?__biz=MzU4MjAzNTAwMA==&mid=2247483802&idx=1&sn=fe11c99170f648f7648fe05b61e05796&chksm=fdbf32cdcac8bbdbff22b6a9444c631c791cfb379a42f887d6fb5402b124c7df1d35efb19f6e#rd","used":true,"who":null},{"_id":"59eedfc2421aa90fe2f02be9","createdAt":"2017-10-24T14:37:54.794Z","desc":"自定义View实现卷尺效果，博客实现原理分析+github开源","publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/06e65ef3f3f1","used":true,"who":null},{"_id":"59eef58d421aa90fef2034c8","createdAt":"2017-10-24T16:10:53.831Z","desc":"Idea 插件，OK, Gradle! 用于快速搜索 maven 库引用","images":["http://img.gank.io/7fb55566-e8c5-4235-8147-df2d7b80b60f"],"publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"https://github.com/scana/ok-gradle","used":true,"who":"drakeet"},{"_id":"59f055d8421aa90fe50c01a2","createdAt":"2017-10-25T17:14:00.412Z","desc":"Android 弹性动画的三种实现方式","publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"http://blog.csdn.net/qq_34902522/article/details/77651799","used":true,"who":null},{"_id":"59f164c9421aa90fe50c01a7","createdAt":"2017-10-26T12:30:01.836Z","desc":"Android 开发面试 \u201c108\u201d 问","images":["http://img.gank.io/2614d7f0-ff4a-49e1-9b61-8908c372dfd5"],"publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"https://www.diycode.cc/topics/993","used":true,"who":null},{"_id":"59f1b7b1421aa90fe2f02bfa","createdAt":"2017-10-26T18:23:45.430Z","desc":"使用 Vue 簡單打包 elevator.js 組件","images":["http://img.gank.io/9431dbbc-4976-41fb-8bd3-d06f85a5c376"],"publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"https://git.io/vFJeQ","used":true,"who":"WesleyChang"},{"_id":"59f212a9421aa90fe72535c9","createdAt":"2017-10-27T00:51:53.772Z","desc":"今天早上，Google 发布了 AS 3.0，因为之前一直在看 kotlin的支持，特地翻了一下对 Java8 的支持方式，结果\u2026\u2026","images":["http://img.gank.io/a0cbee78-0495-4237-b30f-209d4b9a6111"],"publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"https://kymjs.com/code/2017/10/26/01/","used":true,"who":"张涛"},{"_id":"59f29720421aa90fe2f02bfc","createdAt":"2017-10-27T10:17:04.728Z","desc":"LowPloy 风格的动画","images":["http://img.gank.io/6ba882f8-d473-413b-b93c-c094a4fa549b"],"publishedAt":"2017-10-27T12:02:30.376Z","source":"web","type":"Android","url":"https://github.com/nekocode/TriangulationDrawable#","used":true,"who":"nekocode"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Serializable{
        /**
         * _id : 59f6a9ef421aa90fef2034dd
         * createdAt : 2017-10-30T12:26:23.779Z
         * desc : Android Studio3.0填坑指南
         * images : ["http://img.gank.io/64396106-8bf6-4038-9c14-0e309835296b"]
         * publishedAt : 2017-10-31T12:25:55.217Z
         * source : web
         * type : Android
         * url : http://www.jianshu.com/p/b45d68c98828
         * used : true
         * who : 阿韦
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
