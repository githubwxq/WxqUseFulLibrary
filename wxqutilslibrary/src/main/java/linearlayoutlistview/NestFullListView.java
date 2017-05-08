package linearlayoutlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/1.
 *可以实现listview 复用的linearlayout listview中嵌套listview用这个代替
 */
public class NestFullListView extends LinearLayout {
    private LayoutInflater mInflater;
    private List<NestFullViewHolder> mVHCahces;//缓存ViewHolder,按照add的顺序缓存，
    private OnItemClickListener mOnItemClickListener;// 子项点击事件



    public NestFullListView(Context context) {
        super(context);
    }

    public NestFullListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }


    public NestFullListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
mInflater=LayoutInflater.from(context);
        mVHCahces=new ArrayList<NestFullViewHolder>();



    }
    /**
     * 设置点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    private NestFullListViewAdapter mAdapter;//mAdapter 适配器协助

    /**
     * 外部调用  同时刷新视图
     *
     * @param mAdapter
     */
    public void setAdapter(NestFullListViewAdapter mAdapter) {
        this.mAdapter = mAdapter;
        updateUI();
    }

    public void updateUI() {
        if(null!=mAdapter){
            if (null != mAdapter.getDatas() && !mAdapter.getDatas().isEmpty()) {
                //数据源有数据
                if (mAdapter.getDatas().size() > getChildCount() - getFooterCount()) {//数据源大于现有子View不清空

                } else if (mAdapter.getDatas().size() < getChildCount() - getFooterCount()) {//数据源小于现有子View，删除后面多的
                    removeViews(mAdapter.getDatas().size(), getChildCount() - mAdapter.getDatas().size() - getFooterCount());
                    //删除View也清缓存
                    while (mVHCahces.size() > mAdapter.getDatas().size()) {
                        mVHCahces.remove(mVHCahces.size() - 1);
                    }
                }
                for (int i = 0; i < mAdapter.getDatas().size(); i++) {
                    NestFullViewHolder holder;
                    if (mVHCahces.size() - 1 >= i) {//说明有缓存，不用inflate，否则inflate
                        holder = mVHCahces.get(i);
                    } else {
                        holder = new NestFullViewHolder(getContext(), mInflater.inflate(mAdapter.getItemLayoutId(), this, false));
                        mVHCahces.add(holder);//inflate 出来后 add进来缓存
                    }
                    mAdapter.onBind(i, holder);
                    //如果View没有父控件 添加
                    if (null == holder.getConvertView().getParent()) {
                        this.addView(holder.getConvertView(), getChildCount() - getFooterCount());
                    }

                    /* 增加子项点击事件 */
                    final int mPosition = i;
                    holder.getConvertView().setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null && mAdapter != null) {
                                mOnItemClickListener.onItemClick(NestFullListView.this, v, mPosition);
                            }
                        }
                    });
                }
            } else {
                removeViews(0, getChildCount() - getFooterCount());//数据源没数据 清空视图
            }

        }else{


        }


    }


    /******
     * 2016 10 11 add 增加FooterView
     */
    private List<View> mFooterViews;//暂时用存View，觉得可以不存。还没考虑好

    public int getFooterCount() {
        return mFooterViews != null ? mFooterViews.size() : 0;
    }

    public void addFooterView(View footer) {
        if (null == mFooterViews) {
            mFooterViews = new ArrayList<>();
        }
        mFooterViews.add(footer);
        addView(footer);
    }

    /**
     * 在指定位置插入footerview
     *
     * @param pos
     * @param footer
     */
    public void setFooterView(int pos, View footer) {
        if (null == mFooterViews || mFooterViews.size() <= pos) {
            addFooterView(footer);
        } else {
            mFooterViews.set(pos, footer);
            //5 item 1 footer , pos 0, getchildcout =6, remove :
            int realPos = getChildCount() - getFooterCount() + pos;
            removeViewAt(realPos);
            addView(footer, realPos);
        }
    }

    /**
     * 子项点击事件的接口
     */
    public interface OnItemClickListener {

        void onItemClick(NestFullListView parent, View view, int position);
    }
}


    // 给线性布局设置控件
//    NestFullListView allchildInfo=(NestFullListView)viewHolder.getView(R.id.allinfo);
//allchildInfo.setAdapter(new NestFullListViewAdapter<String>(R.layout.school_daka_list_everyone_item,child) {
//@Override
//public void onBind(int pos, String every, NestFullViewHolder holder) {
//        //
//
//        LinearLayout ll_left=   holder.getView(R.id.ll_left);
//        LinearLayout ll_right=   holder.getView(R.id.ll_right);
//
//        TextView tv_left_time= (TextView)holder.getView(R.id.tv_left_time);
//        TextView tv_left_detail= (TextView)holder.getView(R.id.tv_left_detail);
//
//
//        TextView  tv_right_time= (TextView)holder.getView(R.id.tv_right_time);
//        TextView tv_right_detail= (TextView)holder.getView(R.id.tv_right_detail);
//
//
//        if(every.equals("in")){
//        ll_left.setVisibility(View.VISIBLE);
//        tv_left_time.setVisibility(View.VISIBLE);
//        tv_left_detail.setVisibility(View.VISIBLE);
//        ll_right.setVisibility(View.GONE);
//        tv_right_time.setVisibility(View.GONE);
//        tv_right_detail.setVisibility(View.GONE);
//        }
//        if (every.equals("out")){
//        ll_left.setVisibility(View.GONE);
//        tv_left_time.setVisibility(View.GONE);
//        tv_left_detail.setVisibility(View.GONE);
//        ll_right.setVisibility(View.VISIBLE);
//        tv_right_time.setVisibility(View.VISIBLE);
//        tv_right_detail.setVisibility(View.VISIBLE);
//        }
//
//
//
//
//
//        }
//        });
//        }
//        });