package me.iwf.photopicker.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author dongzheng
 * @version V_5.0.0
 * @date 2016/8/26
 * @description
 */
public class BimpUtils {
    public static String ADDPICCON = "";
    public static int imgWidth = 960;
    public static int imgHeight = 1280;
    public static String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/wxq/pickImgCache/";

    public static Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= imgWidth)
                    && (options.outHeight >> i <= imgHeight)) {
                in = new BufferedInputStream(
                        new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                options.inScaled = true;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }

    public static void deleteFile(final Context ctx) {
        final File file = new File(savePath);
        new Thread() {
            @Override
            public void run() {
                if (file.isDirectory()) {
                    File[] filelist = file.listFiles();
                    if (filelist != null) {
                        for (int i = 0; i < filelist.length; i++) {
                            filelist[i].delete();
                            ctx.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                    Uri.fromFile(filelist[i])));
                        }
                    }
//                    notifyFileSystemChanged("file://" + savePath,ctx);
                }
            }
        }.start();

    }

    public static ArrayList<String> getUploadDir(Context context) {
        ArrayList<String> uploadDir = new ArrayList<>(8);
        File file = new File(savePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        ArrayList<String> drr = ImagePreference.getInstance(context).getImagesList(ImagePreference.DRR);
        if (drr.size() != 0) {
            try {
                for (int k = 0; k < drr.size(); k++) {
                    String path = drr.get(k);
                    Bitmap bm = revitionImageSize(path);
                    int degree = CommonUtils.readPictureDegree(path);
                    if (degree != 0) {// 旋转照片角度
                        bm = CommonUtils.rotateBitmap(bm, degree);
                    }
                    String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")) + System.currentTimeMillis() + ".jpg";
                    CommonUtils.saveBitmap(bm, newStr);
                    uploadDir.add(savePath + newStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ImagePreference.getInstance(context).storeImagesList(ImagePreference.UPLOADDIR, uploadDir);
        return uploadDir;
    }

}
