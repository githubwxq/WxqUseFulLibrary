package com.example.wxq.wxqutilslibrary.http.rxandretrofitutils.subscriber.xutils;

/**
 * Created by Administrator on 2016/7/5.
 */
public class XutilsHelp {

    // public static final String IP = "http://www.citycai.com/ticket.jsp";//
    // 静态常量赋初值
    public static final String IP = "";// app首页配置信息接口

    private static XutilsHelp instance;
//获取单列管理整个请求
    public static XutilsHelp getInstance() {
        if (null == instance) {
            instance = new XutilsHelp();
        }
        return instance;
    }

//传一个回调接口把 人家接口回调的东西处理完以后继续回调



}
