package emoji;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.wxq.wxqutilslibrary.R;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;

import commontools.CommonTools;

/**
 * @author wxq
 * @version V_5.0.0
 * @date 2016/9/20 0020
 * @description 表情控件封装
 *  ExpressionView  emoji=(ExpressionView)findViewById(R.id.text_emoji);
 *EditText  et_msg=(EditText)findViewById(R.id.et_msg);
 *emoji.initEmojiView(this,et_msg,length);
 *emoji.setEmoijVisiable(true);
 */
public class ExpressionView extends LinearLayout implements AdapterView.OnItemClickListener {
    private Context mContext;
    private  EditText mEditText;
    private LinearLayout emoji_relative;
    private ViewPager emojipager;
    private LinearLayout  indicator;
    private String[] s;
    private ArrayMap<String, Integer> map = new ArrayMap<>(100);
    private ArrayList<GridView> gvList = new ArrayList<>(10);
    private ArrayList<ImageView> imageViews = new ArrayList<>(10);
    private int curPosition = 0;
   private int maxLength;
    public ExpressionView(Context context) {
        super(context);
        initView(context);
    }

    public ExpressionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ExpressionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void  initEmojiView(Context context,EditText editText ,int length){

        mContext = context;
        mEditText=editText;
        maxLength=length;
        initEmoji();
        dealWithEdit();
    }

    public void setEmoijVisiable(boolean visiable){
        if(visiable){
            emoji_relative.setVisibility(View.VISIBLE);
        }else{
            emoji_relative.setVisibility(View.GONE);
        }

    }


    private void initView(Context context) {
        //加载布局
        View view = View.inflate(context, R.layout.expression, this);
        emoji_relative=(LinearLayout)view.findViewById(R.id.emoji_relative);
        indicator=(LinearLayout)view.findViewById(R.id.indicator);
        emojipager=(ViewPager)view.findViewById(R.id.emojipager) ;
   //     emojipager=(MyViewPager)view.findViewById(R.id.emojipager);

    }

    private void dealWithEdit() {
        //输入框
//        mEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//              //  emoji_relative.setVisibility(View.GONE);
//                //    ll_show_pic.setVisibility(View.GONE);
//            }
//        });

//        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//               // emoji_relative.setVisibility(View.GONE);
//
//            }
//        });


        mEditText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    boolean flag = false;
                    String s = mEditText.getText().toString();
                    if (mEditText.getSelectionStart() != s.length()) {
                        String temp = s.substring(0, mEditText.getSelectionStart());
                        if (temp.endsWith("]")) {
                            if (temp.contains("[")) {
                                int i = temp.lastIndexOf("[");
                                String left = temp.substring(0, i);
                                String rigth = s.substring(temp.length(), s.length());
                                String delete = temp.substring(i, temp.length());
                                String[] stringArray = getResources().getStringArray(R.array.default_smiley_texts);
                                for (int j = 0; j < stringArray.length; j++) {
                                    if (delete.equals(stringArray[j])) {
                                        flag = true;
                                        break;
                                    }
                                }
                                if (flag) {
                                    mEditText.setText(left + rigth);
                                    mEditText.setSelection(i);
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        if (s.length() > 0) {
                            String last = s.substring(s.length() - 1);
                            if (last.equals("]")) {
                                if (s.contains("[")) {
                                    int i = s.lastIndexOf("[");
                                    String a = s.substring(i, s.length());
                                    String[] stringArray = getResources().getStringArray(R.array.default_smiley_texts);
                                    for (int j = 0; j < stringArray.length; j++) {
                                        if (a.equals(stringArray[j])) {
                                            flag = true;
                                            break;
                                        }
                                    }
                                    if (flag) {
                                        String result = s.substring(0, i);
                                        mEditText.setText(result);
                                        mEditText.setSelection(i);
                                    } else {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }

                            } else {
                                return false;
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }


    private void initEmoji() {
        s = this.getResources().getStringArray(R.array.default_smiley_texts);
        //屏幕宽度
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        //表情间的间隔
        int spacing = CommonTools.dip2px(mContext, 8);
        //表情的宽度（即高度）
        int itemWidth = (screenWidth - spacing * 8) / 7;
        //表情面板的高度
        int gvHeight = itemWidth * 3 + 4 * spacing;

        map = getFaceID();
        ArrayList<String> name = new ArrayList<>();
        int margin = CommonTools.dip2px(mContext, 5);
        for (int i = 0; i < s.length; i++) {
            name.add(s[i]);
            if (name.size() == 20) {
                gvList.add(createGridView(screenWidth, spacing, gvHeight, name, itemWidth));
                name = new ArrayList<>();

                ImageView imageView = new ImageView(mContext);
                LayoutParams lp = new LayoutParams
                        (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.setMargins(margin, margin, margin, margin);
                imageView.setLayoutParams(lp);
                if (gvList.size() == 1) {
                    imageView.setBackgroundResource(R.mipmap.ads_point_pre);
                } else {
                    imageView.setBackgroundResource(R.mipmap.ads_point);
                }
                imageViews.add(imageView);
                indicator.addView(imageView);
            }
        }

        if (name.size() > 0) {
            gvList.add(createGridView(screenWidth, spacing, gvHeight, name, itemWidth));

            ImageView imageView = new ImageView(mContext);
            LayoutParams lp = new LayoutParams
                    (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.setMargins(margin, margin, margin, margin);
            imageView.setLayoutParams(lp);
            imageView.setBackgroundResource(R.mipmap.ads_point);
            imageViews.add(imageView);
            indicator.addView(imageView);
        }
        EmojiPagerAdapter pagerAdapter = new EmojiPagerAdapter(gvList);
        LayoutParams params = new LayoutParams(screenWidth, gvHeight);
        emojipager.setLayoutParams(params);
        emojipager.setAdapter(pagerAdapter);
        emojipager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                imageViews.get(curPosition).setBackgroundResource(R.mipmap.ads_point);
                imageViews.get(position).setBackgroundResource(R.mipmap.ads_point_pre);
                curPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private GridView createGridView(int screenWidth, int spacing, int gvHeight, ArrayList<String> name, int itemWidth) {
        GridView gv = new GridView(mContext);
        gv.setNumColumns(7);
        gv.setSelector(android.R.color.transparent);
        gv.setHorizontalSpacing(spacing);
        gv.setVerticalSpacing(spacing);
        gv.setPadding(spacing, spacing, spacing, spacing);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(screenWidth, gvHeight);
        gv.setLayoutParams(params);
        EmojiGridViewAdapter adapter = new EmojiGridViewAdapter(mContext, itemWidth, name, map);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(this);
        return gv;
    }


    public ArrayMap<String, Integer> getFaceID() {
        // 用反射把资源中的表情图片找出来，通过图片的名字来找
        DecimalFormat format = new DecimalFormat("000");
        ArrayMap<String, Integer> map = new ArrayMap<>(100);
        for (int i = 0; i < s.length; i++) {
            int faceid = 0;
            String getid = "f" + format.format(i + 1);
            try {
                Field field = R.mipmap.class.getDeclaredField(getid);
                faceid = field.getInt(this);//R.mipmap.f001
                map.put(s[i], faceid);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (Exception e) {
            }
        }
        return map;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 处理emogji表情
        Object itemAdapter = parent.getAdapter();
        if (itemAdapter instanceof EmojiGridViewAdapter) {
            EmojiGridViewAdapter adapter = (EmojiGridViewAdapter) itemAdapter;
            if (adapter.getCount() - 1 == position) {
                MsgDelete delete = MsgDelete.getInstance(mContext);
                delete.delete(mEditText);
            } else {
                String name = adapter.getItem(position);
                if (mEditText.getText().toString().length() + name.length() > maxLength) {
                    return;
                }
                int index = mEditText.getSelectionStart();
                StringBuilder sb = new StringBuilder(mEditText.getText().toString());
                sb.insert(index, name);
                //et_msg.setText(CommonTools.getContent(this, et_msg, sb.toString(), map));
                mEditText.setText(sb.toString());
                mEditText.setSelection(index + name.length());

            }
        }
    }
}
