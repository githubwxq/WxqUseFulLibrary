package com.example.wxq.wxqutilslibrary.widget.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 对adapter的封装
 *
 * @author Kevin
 * @date 2015-10-28
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
//
//	//注意: 此处必须要从0开始写
//	private static final int TYPE_NORMAL = 0;// 正常布局类型
//	private static final int TYPE_MORE = 1;// 加载更多类型

    private ArrayList<T> data;

    public MyBaseAdapter(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();// 增加加载更多布局数量
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//	// 返回布局类型个数
//	@Override
//	public int getViewTypeCount() {
//		return 2;// 返回两种类型,普通布局+加载更多布局
//	}

    // 返回当前位置应该展示那种布局类型
//	@Override
//	public int getItemViewType(int position) {
//		if (position == getCount() - 1) {// 最后一个
//			return TYPE_MORE;
//		} else {
//			return getInnerType();
//		}
//	}

    // 子类可以重写此方法来更改返回的布局类型
//	public int getInnerType() {
//		return TYPE_NORMAL;// 默认就是普通类型
//	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyBaseHolder holder;
        if (convertView == null) {
            // 1. 加载布局文件
            // 2. 初始化控件 findViewById
            // 3. 打一个标记
            // 都给holder 的构造方法去做了去做了

            holder = getHolder();// 子类返回具体对象


        } else {

            holder = (MyBaseHolder) convertView.getTag();
        }

        // 4. 根据数据来刷新界面

        holder.setData(getItem(position),position);//刷新数据


        return holder.getRootView();
    }


    // 返回当前页面的holder对象, 必须子类实现
    public abstract MyBaseHolder<T> getHolder();


    //获取当前集合大小
    public int getListSize() {
        return data.size();
    }


    // 加载更多
    public void loadMore(ArrayList<T> moreData) {

        data.addAll(moreData);
        this.notifyDataSetChanged();


    }

    // 刷新
    public void refreshNewData(ArrayList<T> newData) {
        data = newData;
        this.notifyDataSetChanged();


    }


}

// activity使用方法
//class EasyAdapter extends MyBaseAdapter<Book>{
//
//
//    public EasyAdapter(ArrayList<Book> data) {
//        super(data);
//    }
//
//    @Override
//    public MyBaseHolder<Book> getHolder() {
//        return new EasyListHold(Main2Activity.this); //上下文的问题
//    }
//}