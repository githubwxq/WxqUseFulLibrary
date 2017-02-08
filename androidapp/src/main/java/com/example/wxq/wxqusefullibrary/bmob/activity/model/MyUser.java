package com.example.wxq.wxqusefullibrary.bmob.activity.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

public class MyUser extends BmobUser {

    private Boolean sex;
    private String nick;
    private Integer age;

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /* 头像 */
    private BmobFile avatar;
    // 头像链接
    private String avatarUrl ;

    public BmobRelation getPosts() {
        return posts;
    }

    public void setPosts(BmobRelation posts) {
        this.posts = posts;
    }

    private BmobRelation posts;//多对多关系：用于存储喜欢该帖子的所有用户
    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}