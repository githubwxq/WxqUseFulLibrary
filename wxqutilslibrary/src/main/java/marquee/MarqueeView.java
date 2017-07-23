package marquee;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.wxq.wxqutilslibrary.R;

import java.util.List;

/**
 * Created by GongWen on 16/12/20.
 * 跑马灯 各种样式
 */

public class MarqueeView extends ViewFlipper {    //翻转视图 视图切换 图片翻转 实现滑动翻页使用
    //interval 必须大于 animDuration，否则视图将会重叠
    private int interval = 2500;//Item翻页时间间隔
    private int animDuration = 500;//Item动画执行时间
    private Animation animIn, animOut;//进出动画
    private int animInRes = R.anim.bottom_in;
    private int animOutRes = R.anim.top_out;

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeView, 0, 0);
        interval = a.getInt(R.styleable.MarqueeView_marqueeInterval, interval);
        animInRes = a.getResourceId(R.styleable.MarqueeView_marqueeAnimIn, animInRes);
        animOutRes = a.getResourceId(R.styleable.MarqueeView_marqueeAnimOut, animOutRes);
        animDuration = a.getInt(R.styleable.MarqueeView_marqueeAnimDuration, animDuration);
        a.recycle();

        setFlipInterval(interval);
        animIn = AnimationUtils.loadAnimation(getContext(), animInRes);
        animIn.setDuration(animDuration);
        setInAnimation(animIn);
        animOut = AnimationUtils.loadAnimation(getContext(), animOutRes);
        animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    public void setMarqueeFactory(MarqueeAdapter factory) {   //对象抽象带有数据
        factory.setAttachedToMarqueeView(this);
        removeAllViews();
        List<View> mViews = factory.getMarqueeViews(); // 获取适配器总的所有数据
        if (mViews != null) {
            for (int i = 0; i < mViews.size(); i++) {
                addView(mViews.get(i));
            }
        }
    }

    public void setInterval(int interval) {
        this.interval = interval;
        setFlipInterval(interval);
    }

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
        animIn.setDuration(animDuration);
        setInAnimation(animIn);
        animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    public void setAnimInAndOut(Animation animIn, Animation animOut) {
        this.animIn = animIn;
        this.animOut = animOut;
        setInAnimation(animIn);
        setOutAnimation(animOut);
    }

    public void setAnimInAndOut(int animInId, int animOutID) {
        animIn = AnimationUtils.loadAnimation(getContext(), animInId);
        animOut = AnimationUtils.loadAnimation(getContext(), animOutID);
        animIn.setDuration(animDuration);
        animOut.setDuration(animDuration);
        setInAnimation(animIn);
        setOutAnimation(animOut);

    }

    public Animation getAnimIn() {
        return animIn;
    }

    public Animation getAnimOut() {
        return animOut;
    }
}


//shiyon使用方法

//<com.gongwen.marqueen.MarqueeView
//        android:id="@+id/marqueeView2"
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:layout_marginTop="20dp"
//        android:background="#88dddddd"
//        app:marqueeAnimDuration="2000"
//        app:marqueeAnimIn="@anim/right_in"
//        app:marqueeAnimOut="@anim/left_out"
//        app:marqueeInterval="2500"></com.gongwen.marqueen.MarqueeView>
//
//<com.gongwen.marqueen.MarqueeView
//        android:id="@+id/marqueeView3"
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:layout_marginTop="20dp"
//        android:background="#88dddddd"></com.gongwen.marqueen.MarqueeView>


//    final MarqueeAdapter<TextView, String> marqueeAdapter2 = new NoticeMF(this);
//        marqueeAdapter2.setOnItemClickListener(new MarqueeAdapter.OnItemClickListener<TextView, String>() {
//@Override
//public void onItemClickListener(MarqueeAdapter.ViewHolder<TextView, String> holder) {
//        Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
//        }
//        });
//        marqueeAdapter2.setData(datas);
//        marqueeView2.setMarqueeFactory(marqueeAdapter2);
//        marqueeView2.startFlipping();
//
//final MarqueeAdapter<TextView, String> marqueeAdapter6 = new NoticeMF(this);
//        marqueeAdapter6.setOnItemClickListener(new MarqueeAdapter.OnItemClickListener<TextView, String>() {
//@Override
//public void onItemClickListener(MarqueeAdapter.ViewHolder<TextView, String> holder) {
//        Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
//        }
//        });
//        marqueeAdapter6.setData(datas);
//        marqueeView6.setMarqueeFactory(marqueeAdapter6);
//        marqueeView6.startFlipping();
//
//        MarqueeAdapter<TextView, String> marqueeAdapter3 = new NoticeMF(this);
//        marqueeAdapter3.setOnItemClickListener(new MarqueeAdapter.OnItemClickListener<TextView, String>() {
//@Override
//public void onItemClickListener(MarqueeAdapter.ViewHolder<TextView, String> holder) {
//        Toast.makeText(MainActivity.this, holder.data, Toast.LENGTH_SHORT).show();
//        }
//        });
//        marqueeAdapter3.setData(datas);
//        marqueeView3.setMarqueeFactory(marqueeAdapter3);
//        marqueeView3.setAnimInAndOut(R.anim.left_in, R.anim.right_out);
//        marqueeView3.setAnimDuration(2000);
//        marqueeView3.setInterval(2500);
//        marqueeView3.setAnimateFirstView(true);
//        //直接调用startFlipping，setAnimateFirstView并没有生效
//        marqueeView3.startFlipping();
//        mHandler.post(new Runnable() {
//@Override
//public void run() {
////                marqueeView3.startFlipping();
//        }
//        });

//public class ComplexViewMF extends MarqueeAdapter<RelativeLayout, ComplexItemEntity> {
//    private LayoutInflater inflater;
//
//    public ComplexViewMF(Context mContext) {
//        super(mContext);
//        inflater = LayoutInflater.from(mContext);
//    }
//
//    @Override
//    public RelativeLayout generateMarqueeItemView(ComplexItemEntity data) {
//        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.complex_view, null);
//        ((TextView) mView.findViewById(R.id.title)).setText(data.getTitle());
//        ((TextView) mView.findViewById(R.id.secondTitle)).setText(data.getSecondTitle());
//        ((TextView) mView.findViewById(R.id.time)).setText(data.getTime());
//        return mView;
//    }
//}