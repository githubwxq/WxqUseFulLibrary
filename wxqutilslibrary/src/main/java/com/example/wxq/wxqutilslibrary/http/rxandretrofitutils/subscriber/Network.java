package com.example.wxq.wxqutilslibrary.http.rxandretrofitutils.subscriber;


import com.example.wxq.wxqutilslibrary.myutils.log.LogUtils;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wxq on 2016/7/3.
 *管理 每个实体请求累的创建工具  为network 服务的
 */
public class Network {

    // 管理接口的实现类  接口实现类被用来获取封装接口数据的observable 一旦订阅数据被回调给subscriber



    private static final Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static final CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static <T> T getService(Class<T> clazz,String baseUrl) {

        T service = (T)getRetrofit(baseUrl).create(clazz);

        return service;
    }




    private static Retrofit getRetrofit(String baseUrl) {

        return new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(new LogInterceptor()).build())
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    private static class LogInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            LogUtils.i("王晓清打印请求日志咯", "request:" + request.toString());
            okhttp3.Response response = chain.proceed(chain.request());
            long t1 = System.nanoTime();
            long t2 = System.nanoTime();
            LogUtils.i("王晓清打印返回日志咯", String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtils.i("王晓清来打印日志咯", "response body:" + content);
            if (response.body() != null) {
                ResponseBody body = ResponseBody.create(mediaType, content);
                return response.newBuilder().body(body).build();
            } else {
                return response;
            }
        }
    }

}
