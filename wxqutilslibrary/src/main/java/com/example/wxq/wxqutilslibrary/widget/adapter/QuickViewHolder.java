package com.example.wxq.wxqutilslibrary.widget.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wxq on 2016/7/15.
 *  注意getmpotion 是有问题的不是真正的position
 */
public class QuickViewHolder {
    private final SparseArray<View> mViews;
     private  int mposition;//  you由于对象的复用 导致mposition根本就不能真正拿到传过来的position 而是之前viewholder 的position
    private View mConvertView;

// 呆货 这些东西来自于adapter
    public QuickViewHolder(Context context, ViewGroup parent, int layoutId,
                           int position) {
         this.mposition=position;
        this.mViews = new SparseArray<View>();
        mConvertView= LayoutInflater.from(context).inflate(layoutId,parent,false);

        mConvertView.setTag(this); //dou在构造的时候完成这些



    }
    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
public static  QuickViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int position){
if(convertView==null){

    return  new QuickViewHolder(context,parent,layoutId,position);
}

return (QuickViewHolder) convertView.getTag();

}

    public View getConvertView()
    {
        return mConvertView;
    }
    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public QuickViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public QuickViewHolder setImageResource(int viewId, int drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param
     * @return
     */
    public QuickViewHolder setImageBitmap(int viewId, Bitmap bm)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param
     * @param
     * @return
     */
//    public QuickViewHolder setImageByUrl(int viewId, String url)
//    {
//        ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
//                (ImageView) getView(viewId));
//        return this;
//    }

    public int getPosition()
    {
        return mposition;
    }











    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
