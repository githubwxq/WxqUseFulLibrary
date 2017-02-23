package com.example.wxq.wxqusefullibrary.modelmanageer;

import android.content.Context;

public class UserManger {

    private static UserManger instance;

    private Context context;

    private UserManger(Context context) {
        this.context = context;
    }

    public static UserManger getInstance(Context context) {
        if (instance == null) {
            instance = new UserManger(context);
        }
        return instance;
    }
}