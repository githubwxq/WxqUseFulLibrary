package linearlayoutlistview;

import java.util.List;

/**
 * Created by Administrator on 2017/1/1.
 */
public abstract class NestFullListViewAdapter<T> {
    private int mItemLayoutId;
    private List<T> mDatas;


    public NestFullListViewAdapter(int mItemLayoutId,List<T> mDatas){
        this.mItemLayoutId=mItemLayoutId;
        this.mDatas=mDatas;

    }
    public NestFullListViewAdapter(){


    }
    /**
     * 被FullListView调用
     *
     * @param i
     * @param holder
     */
    public void onBind(int i, NestFullViewHolder holder) {
        //回调bind方法，多传一个data过去
        onBind(i, mDatas.get(i), holder);
    }



    /**
     * 数据绑定方法
     *
     * @param pos    位置
     * @param t      数据
     * @param holder ItemView的ViewHolder
     */
    public abstract void onBind(int pos, T t, NestFullViewHolder holder);//jie接口的实现类回调到上一次 父类由子类决定

    public int getItemLayoutId() {
        return mItemLayoutId;
    }

    public void setItemLayoutId(int mItemLayoutId) {
        this.mItemLayoutId = mItemLayoutId;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }


}
