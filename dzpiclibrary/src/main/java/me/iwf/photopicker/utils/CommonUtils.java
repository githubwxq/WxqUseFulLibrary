package me.iwf.photopicker.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.iwf.photopicker.R;

/**
 * @author dongzheng
 * @version V_5.0.0
 * @date 2016/8/24
 * @description
 */
public class CommonUtils {

    private static Toast toast;

    public static int readPictureDegree(String path) {
        int degree = 0;
        if (path != null && !(path.toLowerCase().endsWith(".jpg") || path.toLowerCase().endsWith(".jpeg"))) {
            return degree;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }

    public static void saveBitmap(Bitmap bm, String picName) {
        if (bm != null) {
            try {
//                if (!isFileExist("")) {
//                    File tempf = createSDDir("");
//                }
                File f = new File(BimpUtils.savePath, picName);
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(f);
                bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bm != null && !bm.isRecycled()) {
                    bm.recycle();
                }
                System.gc();
            }
        }
    }

    public static String getMsgCurrentTime() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
        String nowTimeStr = dateFormat.format(nowTime);
        return nowTimeStr;
    }

    //由于实现沉浸式状态栏，导致系统自带的Toast出现问题，所以自定义Toast的View
    public static void showToast(Context context, String content) {
        if (context == null)
            return;
        if (toast == null) {
            Context applicationContext = context.getApplicationContext(); //防止内存泄漏
            toast = new Toast(applicationContext);
            toast.setView(View.inflate(context, R.layout.layout_toast, null));
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(content);
        toast.show();
    }

    public static void showToast(Context context, int resId) {
        if (context == null)
            return;
        if (toast == null) {
            Context applicationContext = context.getApplicationContext(); //防止内存泄漏
            toast = new Toast(applicationContext);
            toast.setView(View.inflate(context, R.layout.layout_toast, null));
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(resId);
        toast.show();
    }

    public static boolean checkPermission(Activity context, Fragment fragment, String[] permission, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(permission[0]) != PackageManager.PERMISSION_GRANTED) {
                if (fragment != null) {
                    fragment.requestPermissions(permission, requestCode);
                } else {
                    context.requestPermissions(permission, requestCode);
                }
                return true;
            }
        }
        return false;
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            cursor.close();
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    public static void deleteImage(Context context, String imagePath) {
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = MediaStore.Images.Media.query(resolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=?",
                new String[]{imagePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            long id = cursor.getLong(0);
            cursor.close();
            Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Uri uri = ContentUris.withAppendedId(contentUri, id);
            resolver.delete(uri, null, null);
        } else {
            File file = new File(imagePath);
            if (file.exists()) {
                file.delete();
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(file)));
            }
        }
    }
}
