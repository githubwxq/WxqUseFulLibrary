package specialtools;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kahuis on 2016/6/12.
 */
public class TextUtils {
    /**
     * 修改单个字段颜色
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @return
     */
    public static SpannableString setTextStyle(String text, String keyWord, int color) {
        SpannableString s = new SpannableString(text);
        Pattern p = Pattern.compile(keyWord);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }
    /**
     * 修改单个字段颜色
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @return
     */
    public static SpannableString setTextStyleSize(String text, String keyWord, int size) {
        SpannableString s = new SpannableString(text);
        Pattern p = Pattern.compile(keyWord);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }
    /**
     * 修改单个字段颜色及大小
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @param 大小dp
     * @return
     */
    public static SpannableString setTextStyle(String text, String keyWord, int color, int size) {
        SpannableString s = new SpannableString(text);
        Pattern p = Pattern.compile(keyWord);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            s.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    /**
     * 修改多个字段、统一颜色
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @return
     */
    public static SpannableString setTextStyle(String text, String[] keyWord, int color) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyWord.length; i++) {
            Pattern p = Pattern.compile(keyWord[i]);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }

    /**
     * 修改多个字段、统一颜色、统一大小
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @param 大小dp
     * @return
     */
    public static SpannableString setTextStyle(String text, String[] keyWord, int color, int[] size) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyWord.length; i++) {
            Pattern p = Pattern.compile(keyWord[i]);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                s.setSpan(new AbsoluteSizeSpan(size[i], true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }

    /**
     * 修改多个字段、多个颜色
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @return
     */
    public static SpannableString setTextStyle(String text, String[] keyWord, int[] color) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyWord.length; i++) {
            Pattern p = Pattern.compile(keyWord[i]);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color[i]), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }

    /**
     * 修改多个字段、多个颜色、多个大小
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @param 大小dp
     * @return
     */
    public static SpannableString setTextStyle(String text, String[] keyWord, int[] color, int size[]) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyWord.length; i++) {
            Pattern p = Pattern.compile(keyWord[i]);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color[i]), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                s.setSpan(new AbsoluteSizeSpan(size[i], true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }

    /**
     * 修改多个字段、多个颜色、统一大小
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @param 大小dp
     * @return
     */
    public static SpannableString setTextStyle(String text, String[] keyWord, int[] color, int size) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyWord.length; i++) {
            Pattern p = Pattern.compile(keyWord[i]);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color[i]), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                s.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }

    /**
     * 修改多个字段、统一颜色、统一大小
     *
     * @param 文本
     * @param 修改关键字
     * @param 颜色
     * @param 大小dp
     * @return
     */
    public static SpannableString setTextStyle(String text, String[] keyWord, int color, int size) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyWord.length; i++) {
            Pattern p = Pattern.compile(keyWord[i]);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                s.setSpan(new AbsoluteSizeSpan(size, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }

    public static String format2f(double d) {
        return String.format("%.2f",d);
    }

    public static String formatName(String name) {
        if(name == null) {
            return name;
        }
        if(name.length() < 5) {
            return name;
        } else {
            return name.substring(0,3).concat("...");
        }
    }

    public static String formatMoney(double d) {
        String w = "";
        if(d > 100000) {
            d = d / 100000;
            w="万";
        }
        return String.format("%.2f",d)+w;
    }

    public static String format2int(int d) {
        if(d < 10 && d >=0) {
            return "0" + d;
        }
        return String.valueOf(d);
    }

    /**
     * 带分号的字符串 14,200.00
     * @param d
     * @return
     */
    public static String formatSemicolon2f(double d) {
        NumberFormat nf = new DecimalFormat(",###.##");
        String s = nf.format(d);
        if(s.lastIndexOf(".") == -1) {
           return s.concat(".00");
        }
        return s;
    }

}
