package com.example.wxq.wxqusefullibrary.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.wxq.wxqusefullibrary.Book;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.widget.adapter.BaseAdapterHelper;
import com.example.wxq.wxqutilslibrary.widget.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestCommonAdapterActivity extends AppCompatActivity {

    @BindView(R.id.lv_test_adapter)
    ListView lvTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_common_adapter);

        ButterKnife.bind(this);
        Book book1=new Book("1","a");
        book1.setTag(0);
        Book book2=new Book("2","a");
        book2.setTag(1);
        Book book3=new Book("3","a");
        book3.setTag(2);
        Book book4=new Book("1","a");
        book4.setTag(0);
        Book book5=new Book("2","a");
        book5.setTag(2);
        Book book6=new Book("3","a");

        Book book7=new Book("3","a");
        ArrayList <Book> list=new ArrayList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);

        list.add(book4);

        list.add(book5);

        lvTestAdapter.addHeaderView(View.inflate(this,R.layout.activity_dialog,null));


//        lvTestAdapter.setAdapter(new CommonAdapter<Book>(this,R.layout.book_list_item,list) {
//
//
//            @Override
//            public void onUpdate(BaseAdapterHelper helper, Book item, int position) {
//
//                helper.setText(R.id.tv_bookname,item.getName());
//            }
//        });


//        lvTestAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position >0) {
//                    TextView a =(TextView)view.findViewById(R.id.tv_price);
//                    a.setText("你点击的位置为"+position);//头布局
//                }else{
//                    Toast.makeText(TestCommonAdapterActivity.this, "你点击了头", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
        lvTestAdapter.setAdapter(new MultLayoutAdapter(this,R.layout.book_list_item,list));

    }
private final  class  MultLayoutAdapter extends CommonAdapter<Book>{


    public MultLayoutAdapter(Context context, int layoutResId, List<Book> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onUpdate(BaseAdapterHelper helper, Book item, int position) {
switch (item.getTag()){
    case 0:
        helper.setText(R.id.tv_bookname,item.getName());
        helper.setText(R.id.tv_price,item.getPrice());
        break;
    case 1:
        helper.setText(R.id.tv_price,item.getPrice());
        break;
    case 2:
        helper.setText(R.id.tv_bookname,item.getName());
        break;

}


    }

    @Override
    public int getLayoutResId(Book item, int position) {
        int layoutResId=-1;
        switch (item.getTag()){
            case 0: //布局样式一
                   layoutResId = R.layout.book_list_item;
                   break;
            case 1: //布局样式一
                layoutResId = R.layout.book_list_item2;
                break;
            case 2: //布局样式一
                layoutResId = R.layout.book_list_item3;
                break;
//            case 3: //布局样式一
//                layoutResId = R.layout.item_none_picture;
//                break;


        }


        return layoutResId;
    }
}

    //多布局文件
//    private final class MultipleLayoutAdapter extends CommonRecyclerAdapter<Book>{
//
//        public MultipleLayoutAdapter(Context context, int layoutResId, List<Book> data) {
//            super(context, layoutResId, data);
//        }
//        //多种布局重写此方法即可
//        @Override public int getLayoutResId(Book item, int position) {
//            int layoutResId = -1;
//            switch (item.getName()){
//                case News.TYPE_NONE_PICTURE: //布局样式一
//                    layoutResId = R.layout.item_none_picture;
//                    break;
//                case News.TYPE_SINGLE_PICTURE: //布局样式二
//                    layoutResId = R.layout.item_single_picture;
//                    break;
//                case News.TYPE_MULTIPLE_PICTURE: //布局样式三
//                    layoutResId = R.layout.item_multiple_picture;
//                    break;
//
//                更多的布局样式 ...
//            }
//            return layoutResId;
//        }
//
//        @Override public void onUpdate(BaseAdapterHelper helper, News item, int position) {
//            helper.setImageLoad(new GlideImageLoad());
//            switch (item.getNewsType()){
//                case News.TYPE_NONE_PICTURE: //布局样式一
//                    helper.setText(R.id.xxx, item.getTitle())
//                            .setImageUrl(R.id.xxx,item.getCoverUrl());
//                    break;
//                case News.TYPE_SINGLE_PICTURE: //布局样式二
//                    helper.setText(R.id.xxx, item.getTitle())
//                            .setImageUrl(R.id.xxx,item.getCoverUrl());
//                    break;
//                case News.TYPE_MULTIPLE_PICTURE: //布局样式三
//                    helper.setText(R.id.xxx, item.getTitle())
//                            .setImageUrl(R.id.xxx,item.getCoverUrl());
//                    break;
//
//                更多的布局样式 ...
//            }
//        }
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
