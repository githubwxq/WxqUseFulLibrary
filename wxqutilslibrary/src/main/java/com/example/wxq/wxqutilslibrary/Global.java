package com.example.wxq.wxqutilslibrary;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class Global {
     public static  final  String ERROREXCEPTION= Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wxq/ErrorException";
    public static  final  String GLIDEPIC =Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wxq/GlidePic";
    public static  final  String IMAGELOADPIC="wxq/ImageLoadPic";
    public static  final  String SAVEIMAGE=Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wxq/SaveImage";



}
