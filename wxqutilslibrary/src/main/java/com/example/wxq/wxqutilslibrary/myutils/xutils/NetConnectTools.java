package com.example.wxq.wxqutilslibrary.myutils.xutils;

import android.content.Context;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Toast;

import com.example.wxq.wxqutilslibrary.widget.dialog.CommonAlertDialog;
import com.example.wxq.wxqutilslibrary.widget.dialog.CommonLoadDialog;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import commontools.ToastUtils;

/**
 * @author wxq
 * @version V_5.0.0
 * @date 2016/10/10 0010
 * @description
 */
public class NetConnectTools {
    private static NetConnectTools network;
    private Callback.Cancelable cancelable;
    public static final int PUT = 0;
    public static final int DELETE = 1;
    public static final int Move = 2;
    public static final int POST = 3;

    public static String urlHOst = "";
    public static String uploadHost = "";

    private NetConnectTools() {

    }


    public static NetConnectTools getInstance() {
        if (network == null) {
            synchronized (NetConnectTools.class) {
                if (network == null) {
                    network = new NetConnectTools();
                }
            }
        }
        return network;

    }

    //get请求
    public void getData(String url, ArrayMap<String, String> header, final CallBackListen listen, Context context) {

        if (NetworkUtils.isNetworkAvailable(context)){
            // 有网络
            RequestParams params = new RequestParams(url);
            CommonLoadDialog.getInstance().createAlertDialog(context).show();
            if (header != null) {
                for (Map.Entry<String, String> stringStringEntry : header.entrySet()) {
                    params.addHeader(stringStringEntry.getKey(), stringStringEntry.getValue());
                }
            }
            x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    listen.onSuccess(result);
                }


                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    listen.onError(ex, isOnCallback);
                }

                @Override
                public void onCancelled(CancelledException cex) {
                }

                @Override
                public void onFinished() {
                    listen.onFinished();
                    //加载框消失
                    CommonLoadDialog.getInstance().dismiss();
                }
            });
        }else{
            // 无网络
            Toast.makeText(context,"无网络",Toast.LENGTH_LONG).show();

        }


    }


    //post请求
    public void postData(String url, ArrayMap<String, String> header, android.support.v4.util.ArrayMap<String, String> body, String jsonObject, final CallBackListen listen,Context context) {
        if (NetworkUtils.isNetworkAvailable(context)){
            // 有网络

        }else{
            // 无网络
            ToastUtils.showToast("无网络");

        }
        RequestParams params = new RequestParams(url);
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                params.addHeader(entry.getKey(), entry.getValue());
            }

        }

        if (body != null) {
            for (Map.Entry<String, String> entry : body.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }

        params.setAsJsonContent(true);//作为json数据
        params.setBodyContent(jsonObject);
        params.setCharset("UTF-8");


        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listen.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listen.onError(ex, isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                listen.onFinished();
            }
        });

    }

    //Request请求调用
    public void requestData(String url, android.support.v4.util.ArrayMap<String, String> map, String jsonObject, int method, final CallBackListen listen) {
        HttpMethod httpMethod = null;
        RequestParams params = new RequestParams(url);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addHeader(entry.getKey(), entry.getValue());
            }
        }
        params.setAsJsonContent(true);
        params.setBodyContent(jsonObject);
        if (method == 0) {
            httpMethod = HttpMethod.PUT;
        } else if (method == 1) {
            httpMethod = HttpMethod.DELETE;
        } else if (method == 2) {
            httpMethod = HttpMethod.MOVE;
        } else if (method == 3) {
            httpMethod = HttpMethod.POST;
        }
        x.http().request(httpMethod, params, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listen.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listen.onError(ex, isOnCallback);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                listen.onFinished();

            }
        });
    }

    //文件下载调用
    public Callback.Cancelable loadFile(String url, boolean autoRename, String saveFilepath, final ProgressCallBackListen listen) {
        RequestParams params = new RequestParams(url);
        params.setAutoResume(autoRename);
        params.setSaveFilePath(saveFilepath);
        cancelable = x.http().get(params, new org.xutils.common.Callback.ProgressCallback<File>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                listen.onStarted();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                listen.onLoading(total, current, isDownloading);
            }

            @Override
            public void onSuccess(File result) {
                listen.onSuccess(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listen.onError(ex, isOnCallback);

            }

            @Override
            public void onCancelled(CancelledException cex) {
                listen.onCancelled(cex);

            }

            @Override
            public void onFinished() {

            }
        });
        return cancelable;
    }

    //多图片上传调用
    public void upLoadPicture(String url, android.support.v4.util.ArrayMap<String, String> map, final ArrayList<String> list, final CallBackListen listen) {

        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addHeader(entry.getKey(), entry.getValue());
            }
        }
        //   params.addBodyParameter("myFiletype", "png");
//        for (int i = 0; i < list.size(); i++) {
//            File file = new File(list.get(list.size() - 1 - i));
//            if (file != null) {
//                params.addBodyParameter("File" + i, file);
//            }
//        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listen.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listen.onError(ex, isOnCallback);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                listen.onFinished();

            }
        });

    }


    //单图片上传调用
    public void upLoadPicture(String url, android.support.v4.util.ArrayMap<String, String> map, String picLoad, final CallBackListen listen) {

        RequestParams params = new RequestParams(url);

        params.setMultipart(true);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addHeader(entry.getKey(), entry.getValue());
            }
        }
        //  params.addBodyParameter("myFiletype", "png");
        File file = new File(picLoad);
        params.addBodyParameter("File", file);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                listen.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listen.onError(ex, isOnCallback);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                listen.onFinished();

            }
        });

    }


    public interface CallBackListen {
        void onSuccess(String result);

        void onError(Throwable ex, boolean isOnCallback);

        void onFinished();
    }

    public interface ProgressCallBackListen {

        void onError(Throwable ex, boolean isOnCallback);

        void onCancelled(Callback.CancelledException cex);

        void onStarted();

        void onLoading(long total, long current, boolean isDownloading);

        void onSuccess(File result);
    }


}
