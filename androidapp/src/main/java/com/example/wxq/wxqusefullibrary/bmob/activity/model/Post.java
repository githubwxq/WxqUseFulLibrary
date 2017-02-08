package com.example.wxq.wxqusefullibrary.bmob.activity.model;

import com.example.wxq.wxqusefullibrary.model.Book;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class Post extends BmobObject {
    public ArrayList<Book> books;//不参与关联的集合？？

    private String title;//帖子标题

    private String content;// 帖子内容

    private MyUser author;//帖子的发布者，这里体现的是一对一的关系，该帖子属于某个用户

    private BmobFile image;//帖子图片

    private BmobRelation likes;//多对多关系：用于存储喜欢该帖子的所有用户

    public BmobRelation getComments() {
        return comments;
    }

    public void setComments(BmobRelation comments) {
        this.comments = comments;
    }

    private BmobRelation comments;//多对多关系：用于存储喜欢该帖子的所有评论

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public BmobFile getImage() {
        return image;
    }

    public void setImage(BmobFile image) {
        this.image = image;
    }

    public BmobRelation getLikes() {
        return likes;
    }

    public void setLikes(BmobRelation likes) {
        this.likes = likes;
    }
//自行实现getter和setter方法

}