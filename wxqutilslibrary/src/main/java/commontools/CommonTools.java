package commontools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月18日
 * @description 应用程序的公共工具类
 */
public class CommonTools {
    static MediaPlayer mpalyer;
    static MediaRecorder mr;
    static ProgressDialog progressDialog;
    private static Toast toast;

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f) - 15;
    }

    public static int sp2px(Context context, float spValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    public static void showProgressDialog(Context context, String title,
                                          String msg) {
        if (progressDialog != null && progressDialog.isShowing() == true) {
            return;
        }
        progressDialog = ProgressDialog.show(context, title, msg, true, true);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public static void showProgressDialogLogin(Context context, String title,
                                               String msg) {
        if (progressDialog != null && progressDialog.isShowing() == true) {
            return;
        }
        progressDialog = ProgressDialog.show(context, title, msg, true, true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    /**
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }



    public static String getCurrentTime() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
        String nowTimeStr = dateFormat.format(nowTime);
        return nowTimeStr;
    }

    public static String getCurrentTimeForDaily() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");// 可以方便地修改日期格式
        String nowTimeStr = dateFormat.format(nowTime);
        return nowTimeStr;
    }

    public static String getMsgCurrentTime() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
        String nowTimeStr = dateFormat.format(nowTime);
        return nowTimeStr;
    }

    public static String currentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    public static Date currentDate2() {
        Date date = new Date();
        return date;
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatDateTime(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = "";
        try {
            Date d1 = sdf.parse(time);
            currentDate = sdf.format(d1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (currentDate == null || "".equals(currentDate)) {
            return "";
        }
        Date date = null;
        try {
            date = format.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天
        int year = current.get(Calendar.YEAR);
        int month = current.get(Calendar.MONTH);
        int day = current.get(Calendar.DAY_OF_MONTH);
        today.set(Calendar.YEAR, year);
        today.set(Calendar.MONTH, month);
        today.set(Calendar.DAY_OF_MONTH, day);
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();    //昨天

        yesterday.set(Calendar.YEAR, year);
        yesterday.set(Calendar.MONTH, month);
        yesterday.set(Calendar.DAY_OF_MONTH, day - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        current.setTime(date);
        int year1 = current.get(Calendar.YEAR);
        int month1 = current.get(Calendar.MONTH) + 1;
        int day1 = current.get(Calendar.DAY_OF_MONTH);
        int hour1 = current.get(Calendar.HOUR_OF_DAY);
        int minute1 = current.get(Calendar.MINUTE);
        String syear = String.valueOf(year1);
        String smonth = String.valueOf(month1);
        String sday = String.valueOf(day1);
        String shour = String.valueOf(hour1);
        String sminute = String.valueOf(minute1);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentflag = simpleDateFormat.format(current.getTime());
//        String todayflag = simpleDateFormat.format(today.getTime());
//        String yesterdayflag = simpleDateFormat.format(yesterday.getTime());
        if (current.after(today)) {
            if (hour1 < 10) {
                shour = "0" + hour1;
            }
            if (minute1 < 10) {
                sminute = "0" + minute1;
            }
            return shour + ":" + sminute;
        } else if (current.before(today) && current.after(yesterday)) {
            if (hour1 < 10) {
                shour = "0" + hour1;
            }
            if (minute1 < 10) {
                sminute = "0" + minute1;
            }
            return "昨天 " + shour + ":" + sminute;
        } else if (current.get(Calendar.YEAR) < (today.get(Calendar.YEAR))) {
            return currentDate.replaceFirst("-", "年").replace("-", "月").replace(" ", "日  ");
        } else {
            if (month1 < 10) {
                smonth = "0" + month1;
            }
            if (day1 < 10) {
                sday = "0" + day1;
            }
            if (hour1 < 10) {
                shour = "0" + hour1;
            }
            if (minute1 < 10) {
                sminute = "0" + minute1;
            }
            return smonth + "月" + sday + "日" + " " + shour + ":" + sminute;
        }
    }

    public static String currentDateAndTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    public static String currentTimeSong() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        return mMonth + "月" + mDay + "日";
    }

    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    public static String getDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = "";
        try {
            Date d1 = sdf.parse(date);
            currentDate = sdf.format(d1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentDate;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        if (w == 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static String getFirstDayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return sdf.format(c.getTime());
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static String getLastDayOfWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return sdf.format(c.getTime());
    }

    public static String currentTime() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String str0 = String.valueOf(hour);
        String str = String.valueOf(minute);
        String str1 = String.valueOf(second);
        if (hour < 10) {
            str0 = "0" + hour;
        }
        if (minute < 10) {
            str = "0" + minute;
        }
        if (second < 10) {
            str1 = "0" + second;
        }
        return str0 + ":" + str + ":" + str1;
    }

    @SuppressLint("SimpleDateFormat")
    public static int compare_date(String DATE1, String DATE2) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 采用simpleDateFormat处理日期(yyyy-mm-dd
        // hh:mm:ss.0)
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {// 比较long型的毫秒数
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static long compare_int(String a, String b) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Long c;
        long d = 0;
        try {
            c = sf.parse(b).getTime() - sf.parse(a).getTime();
            d = c / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    @SuppressWarnings("deprecation")
    public static ArrayList<String> dateToWeek(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> week = new ArrayList<>();
        try {
            Date parse = sdf.parse(s);
            int b = parse.getDay();
            Date fdate;
            List<Date> list = new ArrayList<Date>();
            Long fTime = parse.getTime() - b * 24 * 3600000;
            for (int a = 1; a <= 7; a++) {
                fdate = new Date();
                fdate.setTime(fTime + (a * 24 * 3600000)); //一周从周日开始算，则使用此方式
                //fdate.setTime(fTime + ((a-1) * 24*3600000)); //一周从周一开始算，则使用此方式
                list.add(a - 1, fdate);
            }
            for (Date date : list) {
                week.add(sdf.format(date));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return week;
        }


    }

    @SuppressWarnings("deprecation")
    public static ArrayList<String> dateToLastWeek(int c) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
//n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = c;
        String monday;
        cal.add(Calendar.DATE, n * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        monday = sdf.format(cal.getTime());
        ArrayList<String> week = new ArrayList<>();
        try {
            Date parse = sdf.parse(monday);
            int b = parse.getDay();
            Date fdate;
            List<Date> list = new ArrayList<Date>();
            Long fTime = parse.getTime() - b * 24 * 3600000;
            for (int a = 1; a <= 7; a++) {
                fdate = new Date();
                fdate.setTime(fTime + (a * 24 * 3600000)); //一周从周日开始算，则使用此方式
                //fdate.setTime(fTime + ((a-1) * 24*3600000)); //一周从周一开始算，则使用此方式
                list.add(a - 1, fdate);
            }
            for (Date date : list) {
                week.add(sdf.format(date));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return week;
        }


    }

    public static String lastOrNextWeek(String data, int flag) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, flag); // 向前一周；如果需要向后一周，用正数即可
        return sdf.format(cal.getTime());
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                // do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        source += " "; // 在传入的source后面加上一个空字符。返回的时候trim掉就OK了
        if (!containsEmoji(source)) {
            return source;// 如果不包含，直接返回
        }
        // 到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;// 如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    public static long dateToLong(String in) {//2016-05-19 13:31:21
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(in);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis();
    }

    public static String format(long time) {
        long[] time_differ = getZeroData();
        String date = "";
        if (time >= time_differ[0]) {
            if (time - time_differ[0] <= 24 * 60 * 60 * 1000) {
                date = secondToDate(time, 0);
            } else {
                date = secondToDate(time, 4);
            }
        } else if (time < time_differ[0] && time >= time_differ[1]) {
            date = secondToDate(time, 1);
        } else if (time < time_differ[1] && time >= time_differ[2]) {
            date = secondToDate(time, 2);
        } else if (time < time_differ[2]) {
            date = secondToDate(time, 3);
        }
        return date;
    }

    private static long[] getZeroData() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time_today = cal.getTimeInMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        long year = cal1.getTimeInMillis();
        long time_yesterday = time_today - 60 * 60 * 24 * 1000;
        long[] time_group = {time_today, time_yesterday, year};
        return time_group;
    }

    private static String secondToDate(long second, int type) {
        Date dat = new Date(second);
        SimpleDateFormat format = null;
        String data = null;
        switch (type) {
            case 0:
                format = new SimpleDateFormat("HH:mm");
                data = "今天 " + format.format(dat);
                break;
            case 1:
                format = new SimpleDateFormat("HH:mm");
                data = "昨天 " + format.format(dat);
                break;
            case 2:
                format = new SimpleDateFormat("MM-dd HH:mm");
                data = format.format(dat);
                break;
            case 3:
                format = new SimpleDateFormat("yyyy-MM-dd");
                data = format.format(dat);
                break;
            case 4:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                data = format.format(dat);
                break;
        }
        return data;
    }

    public static void startActivity(Context context, Class classes) {
        startActivity(context, classes, null);
    }

    public static void startActivity(Context context, Class classes, Bundle bundle) {
        Intent intent = new Intent(context, classes);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
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

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int tmp = 0;
        if (listAdapter.getCount() >= 3) {
            for (int i = 0; i < 3; i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
                tmp = listItem.getMeasuredHeight();
            }
        } else {
            for (int i = 0; i < listAdapter.getCount(); i++) {

                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
                tmp = listItem.getMeasuredHeight();
            }
        }

        // totalHeight += 10;
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private static Bitmap getScaleResourseImg(Context context, int resId, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inDensity = 160;
        options.inScaled = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        if (options.outHeight > height) {
            options.inSampleSize = (int) (options.outHeight * 1.0 / height);
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), resId, options);
    }



    public static int readPictureDegree(String path) {
        int degree = 0;
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

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmailOrPhone(String email) {
        if (null == email || "".equals(email))
            return false;
        // Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)|(1[3|4|5|7|8][0-9]\\d{8})");// 复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 获得手机屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static String getFormatTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date string2Date(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }



    /*
    * 在Android4.4及以上的手机上面使状态栏变成主色调
    * */
//    @TargetApi(19)
//    public static void initWindow(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
//            tintManager.setStatusBarTintColor(activity.getResources().getColor(R.color.account_orange));
//            tintManager.setStatusBarTintEnabled(true);
//        }
//    }

    //数据库批量插入可以用这个方法,添加的顺序按定义的顺序排列
    public static String[] getAllField(String className) {
        String[] strings = new String[2];
        StringBuilder sb = new StringBuilder("");
        StringBuilder sb2 = new StringBuilder("");
        try {
            Class clazz = Class.forName(className);
            Field[] field = clazz.getDeclaredFields();
            for (Field f : field) {
                sb.append(f.getName() + ",");
                sb2.append("?,");
            }
            strings[0] = sb.toString();
            strings[1] = sb2.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    public static boolean isEmpty(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    //把字符串里的多个换成换成一个
    public static String replaceEnter(String content) {
        String str = new String(content);
        try {
            str = str.replaceAll("[\\n]+", "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }



    public static boolean containValue(int[] arrays, int value) {
        for (int i : arrays) {
            if (i == value)
                return true;
        }
        return false;
    }







    public static String getFileType(String string) {
        if (string.equalsIgnoreCase("doc") || string.equalsIgnoreCase("docx")) {
            return "word";
        } else if (string.equalsIgnoreCase("ppt") || string.equalsIgnoreCase("pptx")) {
            return "PPT";
        } else if (string.equalsIgnoreCase("xls") || string.equalsIgnoreCase("xlsx")) {
            return "excel";
        } else if (string.equalsIgnoreCase("txt")) {
            return "txt";
        } else if (string.equalsIgnoreCase("pdf")) {
            return "pdf";
        } else {
            return "";
        }
    }

    /**
     * 检查权限，如果在fragment里面调用，把当前fragment传进来，否则传null
     */
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


    public static long getFileSizes(File f) {
        long s = 0;
        FileInputStream fis = null;
        try {
            if (f.exists()) {
                fis = new FileInputStream(f);
                s = fis.available();
            } else {
                f.createNewFile();
            }
        } catch (Exception e) {

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return s;
    }

    public static void deleteDir(String imgPath) {
        try {
            File dir = new File(imgPath);
            if (dir == null || !dir.exists() || !dir.isDirectory() || dir.listFiles() == null)
                return;
            for (File file : dir.listFiles()) {
                if (file.isFile())
                    file.delete(); // 删除所有文件
                else if (file.isDirectory())
                    deleteDir(file.getAbsolutePath()); // 递规的方式删除文件夹
            }
            dir.delete();// 删除目录本身
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFilesInDir(String path) {
        File dir = new File(path);
        if (dir == null || !dir.exists() || !dir.isDirectory() || dir.listFiles() == null)
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete(); // 删除所有文件
            } else {
                deleteFilesInDir(file.getAbsolutePath());
            }
        }
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String res = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        }
        return res;
    }

    public static Bitmap getRoundRectBitmap(Context context, Bitmap srcBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());
        RectF rectF = new RectF(rect);
        float raidus = CommonTools.dip2px(context, 3);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(rectF, raidus, raidus, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, rect, rect, paint);
        return bitmap;
    }

//    public static SpannableString getContent(Context context, EditText tv, String source, ArrayMap<String, Integer> map) {
//        SpannableString spannableString = new SpannableString(source);
//        String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";
//        Pattern pattern = Pattern.compile(regexEmoji);
//        Matcher matcher = pattern.matcher(spannableString);
//        while (matcher.find()) {
//            final String emojiStr = matcher.group();
//            if (emojiStr != null) {
//                int start = matcher.start();
//                int resId;
//                try {
//                    resId = map.get(emojiStr);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    resId = -1;
//                }
//                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
//                if (bitmap != null) {
//                    int textSize = (int) (tv.getTextSize() * 1.2);
//                    bitmap = Bitmap.createScaledBitmap(bitmap, textSize, textSize, true);
//                    ImageSpan imageSpan = new ImageSpan(context, bitmap);
//                    spannableString.setSpan(imageSpan, start, start + emojiStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//            }
//        }
//        return spannableString;
//    }





//    public static SpannableString getContent(Context context, ExpandableTextView tv, String source, ArrayMap<String, Integer> map) {
//        SpannableString spannableString = new SpannableString(source);
//        String regexEmoji = "\\[[\u4e00-\u9fa5\\w]+\\]";
//        Pattern pattern = Pattern.compile(regexEmoji);
//        Matcher matcher = pattern.matcher(spannableString);
//        while (matcher.find()) {
//            final String emojiStr = matcher.group();
//            if (emojiStr != null) {
//                int start = matcher.start();
//                int resId;
//                try {
//                    resId = map.get(emojiStr);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    resId = -1;
//                }
//                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
//                if (bitmap != null) {
//                    int textSize = (int) (tv.getmTv().getTextSize() * 1.2);
//                    bitmap = Bitmap.createScaledBitmap(bitmap, textSize, textSize, true);
//                    ImageSpan imageSpan = new ImageSpan(context, bitmap);
//                    spannableString.setSpan(imageSpan, start, start + emojiStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//
//            }
//        }
//        return spannableString;
//    }



    public static void scanSingleFile(final Context mContext, String filePath) {
        if (CommonTools.isEmpty(filePath)) {
            return;
        }
        File file = new File(filePath);
        MediaScannerConnection.scanFile(mContext,
                new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        ContentResolver cr = mContext.getContentResolver();
                        long datemodified = 0;
                        long dateadded = 0;
                        if (uri == null) {
                            LogUtil.i("uri == null");
                            return;
                        }
                        Cursor cursor = cr.query(uri, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            datemodified = cursor.getLong(cursor
                                    .getColumnIndex(MediaStore.MediaColumns.DATE_MODIFIED));
                            dateadded = cursor.getLong(cursor
                                    .getColumnIndex(MediaStore.MediaColumns.DATE_ADDED));
                            cursor.close();
                        }
                        ContentValues values = new ContentValues();
                        if (datemodified > 0
                                && String.valueOf(datemodified).length() > 10) {
                            values.put(MediaStore.MediaColumns.DATE_MODIFIED,
                                    datemodified / 1000);
                        }
                        if (dateadded > 0
                                && String.valueOf(dateadded).length() > 13) {
                            values.put(MediaStore.MediaColumns.DATE_ADDED,
                                    dateadded / 1000);
                        }
                        if (values.size() > 0) {
                            cr.update(uri, values, null, null);
                        }
                    }
                });
    }

    /**
     * 显示软键盘
     */
    public static void showInput(EditText et_msg) {
        try {
            et_msg.setFocusable(true);
            et_msg.setFocusableInTouchMode(true);
            et_msg.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) et_msg.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(et_msg, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideInput(EditText et_msg) {
        try {
            InputMethodManager imm = (InputMethodManager) et_msg.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_msg.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把字符串里的回车和空格都替换成一个空格
     */
    public static String replaceEnterAndSpace(String content) {
        String str = new String(content);
        try {
            str = str.replaceAll("[\\n]+", " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean getFileSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return true;
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return true;
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            String s = String.valueOf((size / 100)) + "." + String.valueOf((size % 100));
            float v = Float.valueOf(s).floatValue();
            if (v <= 2) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
