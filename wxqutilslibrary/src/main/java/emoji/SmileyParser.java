package emoji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.util.ArrayMap;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.widget.TextView;


import com.example.wxq.wxqutilslibrary.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月24日
 * @description 应用程序Activity的模板类
 */
public class SmileyParser {
    private Context mContext;
    private String[] mSmileyTexts;
    private Pattern mPattern;
    private ArrayMap<String, Integer> mSmileyToRes;
    public static final int[] DEFAULT_SMILEY_RES_IDS = {
            R.mipmap.f001,
            R.mipmap.f002,
            R.mipmap.f003,
            R.mipmap.f004,
            R.mipmap.f005,
            R.mipmap.f006,
            R.mipmap.f007,
            R.mipmap.f008,
            R.mipmap.f009,
            R.mipmap.f010,
            R.mipmap.f011,
            R.mipmap.f012,
            R.mipmap.f013,
            R.mipmap.f014,
            R.mipmap.f015,
            R.mipmap.f016,
            R.mipmap.f017,
            R.mipmap.f018,
            R.mipmap.f019,
            R.mipmap.f020,
            R.mipmap.f021,
            R.mipmap.f022,
            R.mipmap.f023,
            R.mipmap.f024,
            R.mipmap.f025,
            R.mipmap.f026,
            R.mipmap.f027,
            R.mipmap.f028,
            R.mipmap.f029,
            R.mipmap.f030,
            R.mipmap.f031,
            R.mipmap.f032,
            R.mipmap.f033,
            R.mipmap.f034,
            R.mipmap.f035,
            R.mipmap.f036,
            R.mipmap.f037,
            R.mipmap.f038,
            R.mipmap.f039,
            R.mipmap.f040,
            R.mipmap.f041,
            R.mipmap.f042,
            R.mipmap.f043,
            R.mipmap.f044,
            R.mipmap.f045,

            R.mipmap.f046,
            R.mipmap.f047,
            R.mipmap.f048,
            R.mipmap.f049,
            R.mipmap.f050,
            R.mipmap.f051,
            R.mipmap.f052,
            R.mipmap.f053,
            R.mipmap.f054,
            R.mipmap.f055,
            R.mipmap.f056,
            R.mipmap.f057,
            R.mipmap.f058,
            R.mipmap.f059,
            R.mipmap.f060,
            R.mipmap.f061,
            R.mipmap.f062,
            R.mipmap.f063,
            R.mipmap.f064,
            R.mipmap.f065,
            R.mipmap.f066,
            R.mipmap.f067,
            R.mipmap.f068,
            R.mipmap.f069,
            R.mipmap.f070,
            R.mipmap.f071,
            R.mipmap.f072,
            R.mipmap.f073,
            R.mipmap.f074,
            R.mipmap.f075,
            R.mipmap.f076,
            R.mipmap.f077,
            R.mipmap.f078,
            R.mipmap.f079,
            R.mipmap.f080,
            R.mipmap.f081,
            R.mipmap.f082,
            R.mipmap.f083,
            R.mipmap.f084,
            R.mipmap.f085,
            R.mipmap.f086,
            R.mipmap.f087,
            R.mipmap.f088,
            R.mipmap.f089,
            R.mipmap.f090,
            R.mipmap.f091,
            R.mipmap.f092,

    };

    public SmileyParser(Context context) {
        mContext = context.getApplicationContext();
        mSmileyTexts = mContext.getResources().getStringArray(DEFAULT_SMILEY_TEXTS);
        mSmileyToRes = buildSmileyToRes();
        mPattern = buildPattern();
    }

    public static final int DEFAULT_SMILEY_TEXTS = R.array.default_smiley_texts;

    private ArrayMap<String, Integer> buildSmileyToRes() {
        if (DEFAULT_SMILEY_RES_IDS.length != mSmileyTexts.length) {
//          Log.w("SmileyParser", "Smiley resource ID/text mismatch");
            //表情的数量需要和数组定义的长度一致！
            throw new IllegalStateException("Smiley resource ID/text mismatch");
        }

        ArrayMap<String, Integer> smileyToRes = new ArrayMap<>(mSmileyTexts.length);
        for (int i = 0; i < mSmileyTexts.length; i++) {
            smileyToRes.put(mSmileyTexts[i], DEFAULT_SMILEY_RES_IDS[i]);
        }

        return smileyToRes;
    }

    //构建正则表达式
    private Pattern buildPattern() {
        StringBuilder patternString = new StringBuilder(mSmileyTexts.length * 4);
        patternString.append('(');
        for (String s : mSmileyTexts) {
            patternString.append(Pattern.quote(s));
            patternString.append('|');
        }
        patternString.replace(patternString.length() - 1, patternString.length(), ")");
        return Pattern.compile(patternString.toString());
    }

    //根据文本替换成图片
    public SpannableString replace(CharSequence text) {
        SpannableString builder = new SpannableString(text);
        Matcher matcher = mPattern.matcher(text);
        while (matcher.find()) {
            int resId = mSmileyToRes.get(matcher.group());
            builder.setSpan(new ImageSpan(mContext, resId), matcher.start(), matcher.end(), 0);
        }
        return builder;
    }

    public CharSequence replace(CharSequence text, TextView textView) {
        //先用TextUtils.htmlEncode（）对<,>,&等这种符号进行转义
        String builder = TextUtils.htmlEncode(text.toString().trim());
        Matcher matcher = mPattern.matcher(builder);
        while (matcher.find()) {
            String string = matcher.group();
            Integer resId = mSmileyToRes.get(string);
            if (resId != null) {
                if (builder.indexOf(string) > -1) {
                    builder = builder.replaceAll(Pattern.quote(string), "<img src='" + resId + "'/>");
                }
            }
        }
        return Html.fromHtml(builder.toString().replaceAll("[\\n]", "<br/>"), getImageGetterInstance(textView), null);
    }

    /**
     * @param text     原文字
     * @param textView 显示文字的textview
     * @param color    字体颜色,格式：#33CC00
     * @return
     */
    public CharSequence replace(CharSequence text, TextView textView, String color) {
        //先用TextUtils.htmlEncode（）对<,>,&等这种符号进行转义
        String builder = TextUtils.htmlEncode(text.toString().trim());
        Matcher matcher = mPattern.matcher(builder);
        while (matcher.find()) {
            String string = matcher.group();
            Integer resId = mSmileyToRes.get(string);
            if (resId != null) {
                if (builder.indexOf(string) > -1) {
                    builder = builder.replaceAll(Pattern.quote(string), "<img src='" + resId + "'/>");
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder(500);
        stringBuilder.append("<font color=\"").append(color).append("\">").append(builder.replaceAll("[\\n]", "<br/>")).append("</font>");
        return Html.fromHtml(stringBuilder.toString(), getImageGetterInstance(textView), null);
    }

    public CharSequence replace(CharSequence name, CharSequence text, TextView textView, String color) {
        //先用TextUtils.htmlEncode（）对<,>,&等这种符号进行转义
        String builder = TextUtils.htmlEncode(text.toString().trim());
        Matcher matcher = mPattern.matcher(builder);
        while (matcher.find()) {
            String string = matcher.group();
            Integer resId = mSmileyToRes.get(string);
            if (resId != null) {
                if (builder.indexOf(string) > -1) {
                    builder = builder.replaceAll(Pattern.quote(string), "<img src='" + resId + "'/>");
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder(500);
        // stringBuilder.append(name);
        //: <font style="font-size:12px">文字</font><span style="font-size:12px">设置span内字体大小为14px</span>
        // name字体跟换
        stringBuilder.append("<span style=\"font-size:").append("130").append("px\">").append(name.toString()).append("</span>");
        stringBuilder.append("<font color=\"").append(color).append("\">").append(builder.replaceAll("[\\n]", "<br/>")).append("</font>");


        return Html.fromHtml(stringBuilder.toString(), getImageGetterInstance(textView), null);
    }


    public Html.ImageGetter getImageGetterInstance(final TextView textView) {
        Html.ImageGetter imgGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                int fontH = (int) (textView.getTextSize() * 1.2);
                int id = Integer.parseInt(source);
                Drawable d = textView.getContext().getResources().getDrawable(id);
                int height = fontH;
                int width = (int) ((float) d.getIntrinsicWidth() / (float) d.getIntrinsicHeight()) * fontH;
                if (width == 0) {
                    width = d.getIntrinsicWidth();
                }
                d.setBounds(0, 0, width, height);
                return d;
            }
        };
        return imgGetter;
    }

    public SpannableString replaceInClassInfo(CharSequence text, TextView textView) {
        SpannableString builder = new SpannableString(text);
        Matcher matcher = mPattern.matcher(text);
        while (matcher.find()) {
            int resId = mSmileyToRes.get(matcher.group());
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), resId);
            if (bitmap != null) {
                Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, (int) (textView.getTextSize() * 1.2), (int) (textView.getTextSize() * 1.2), true);
                builder.setSpan(new ImageSpan(mContext, scaleBitmap), matcher.start(), matcher.end(), 0);
                bitmap.recycle();
            }
        }
        return builder;
    }
}
