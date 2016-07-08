package com.example.wxq.wxqusefullibrary;


import com.example.wxq.wxqutilslibrary.http.rxandretrofitutils.subscriber.Network;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/8.
 */
public class  BookServiceImpl {

    public static void getAllStudentByName(Subscriber<Book> subscriber) {

        Network.getService(BookService.class,API.BASE_API_STUDENT)//直接写成类定死 定死就不能用这个空中的这个方法了
                .getAllBook()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }



}
