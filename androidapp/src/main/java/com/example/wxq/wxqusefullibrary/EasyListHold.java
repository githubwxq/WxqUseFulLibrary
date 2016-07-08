package com.example.wxq.wxqusefullibrary;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.wxq.wxqutilslibrary.widget.adapter.MyBaseHolder;

/**
 * Created by Administrator on 2016/7/7.
 */
public class EasyListHold extends MyBaseHolder<Book>{
  private int selectPositon=7;   //位置影响数据
    private TextView tv_bookname;
    private TextView bookprive;


    public EasyListHold(Context context) {
        super(context);
    }


    @Override
    protected View initView() {

       System.out.print("11111111111");
        View view =View.inflate(context, R.layout.book_list_item, null);//view de的特效

        tv_bookname=(TextView)view.findViewById(R.id.tv_bookname);
        bookprive=(TextView)view.findViewById(R.id.tv_price);
        dealListener();
        return view;
    }

    private void dealListener() {



    }


    @Override
    public void refreshView(Book data,int position) { //当前date 当前position 确保与外面的对应
        // 拿数据然后放数据

if (position==selectPositon){


    tv_bookname.setText("点击"+position+data.getName());
}else{
    tv_bookname.setText(position+data.getName());
}



   bookprive.setText(data.getPrice());





    }

    public int getSelectPositon() {
        return selectPositon;
    }

    public void setSelectPositon(int selectPositon) {
        this.selectPositon = selectPositon;
    }
}
