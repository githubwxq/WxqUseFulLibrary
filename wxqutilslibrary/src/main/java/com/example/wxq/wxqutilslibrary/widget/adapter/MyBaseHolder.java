package com.example.wxq.wxqutilslibrary.widget.adapter;

import android.view.View;

/**
 * Created by Administrator on 2016/7/7.
 */
public abstract class MyBaseHolder<T> {
    private View mRootView;
    private T data;
//当new对象的时候就会加载布局初始化控件，以及设置tag


    public MyBaseHolder() {

        mRootView=initView();//大家不同的实现就用抽象方法实现不同内容
        mRootView.setTag(this);//把当前类给他 呆货取出来

    }

    // 1. 加载布局文件
    // 2. 初始化控件 findViewById
    protected abstract View initView();

    // 返回item的布局对象
    public View getRootView() {
        return mRootView;
    }
    // 设置当前item的数据
    public void setData(T data,int position) {
        this.data = data;
        refreshView(data,position);
    }
    // 获取当前item的数据
    public T getData() {
        return data;
    }

    // 4. 根据数据来刷新界面
    public abstract void refreshView(T data,int position);


}

//使用方法
//package com.example.wxq.wxqusefullibrary;
//
//        import android.content.Context;
//        import android.view.View;
//        import android.widget.TextView;
//
//        import com.example.wxq.wxqutilslibrary.widget.adapter.MyBaseHolder;
//
///**
// * Created by Administrator on 2016/7/7.
// */
//public class EasyListHold extends MyBaseHolder<Book>{
//
//    private TextView tv_bookname;
//    private TextView bookprive;
//
//
//    public EasyListHold(Context context) {
//        super(context);
//    }
//
//
//    @Override
//    protected View initView() {
//
//        System.out.print("11111111111");
//        View view =View.inflate(context, R.layout.book_list_item, null);//view de的特效
//
//        tv_bookname=(TextView)view.findViewById(R.id.tv_bookname);
//        bookprive=(TextView)view.findViewById(R.id.tv_price);
//        dealListener();
//        return view;
//    }
//
//    private void dealListener() {
//
//
//
//    }
//
//
//    @Override
//    public void refreshView(Book data) {
//        // 拿数据然后放数据
//        tv_bookname.setText(data.getName());
//        bookprive.setText(data.getPrice());
//    }
//}
