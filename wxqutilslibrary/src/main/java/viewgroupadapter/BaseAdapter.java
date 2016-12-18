package viewgroupadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
public abstract class BaseAdapter<T> implements  IViewGroupAdapter {
    public List<T> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public List<T> mDatas;
    public  Context mContext;
    public LayoutInflater mInflater;

    public BaseAdapter(Context context,List<T> datas ){
        mDatas=datas;
        mContext=context;
        mInflater=LayoutInflater.from(mContext);

    }


    @Override
    public View getView(ViewGroup parent, int pos) {
        return getView(parent ,pos,mDatas.get(pos));
    }

    public abstract View getView(ViewGroup parent, int pos, T t); // 由子类完成 特殊的给子类 相同的父类处理



    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }



}
