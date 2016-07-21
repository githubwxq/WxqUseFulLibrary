package com.example.wxq.wxqusefullibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxq.wxqutilslibrary.widget.adapter.MyBaseAdapter;
import com.example.wxq.wxqutilslibrary.widget.adapter.MyBaseHolder;
import com.example.wxq.wxqutilslibrary.widget.adapter.QuickAdapter;
import com.example.wxq.wxqutilslibrary.widget.adapter.QuickViewHolder;
import com.example.wxq.wxqutilslibrary.widget.listview.RefreshListView;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    RefreshListView easy_list;
    Button btn_next;
    EasyListHold easyListHold;
    ArrayList<Book> data2;
    Button btnTabs;
    ArrayList<Book> data;
    QuickAdapter<Book> quickAdapter;
    int selectPosition;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        RxBus.get().register(this);
        btnTabs = (Button) findViewById(R.id.btn_tabs);
        btnTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, TabsActivity.class));

            }
        });
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Book book = new Book("main2activiy ", "1000");

                RxBus.get().post(book);

                Intent intent = new Intent(Main2Activity.this, MyRxActivity.class);
                startActivity(intent);
            }
        });
        //   easyListHold = new EasyListHold(this);

        easy_list = (RefreshListView) findViewById(R.id.easy_list);
        data = new ArrayList<>();
        data.add(new Book("wxq", "14"));
        data.add(new Book("wxq", "15"));
        data.add(new Book("wxq", "19"));
        data.add(new Book("wxq", "20"));
        data.add(new Book("wxq", "21"));
        data.add(new Book("wxq", "22"));
        data.add(new Book("wxq", "23"));
        data.add(new Book("wxq", "24"));
        data.add(new Book("wxq", "25"));
        data.add(new Book("wxq", "56"));
        data.add(new Book("wxq", "24"));

        data2 = new ArrayList<>();
        data2.add(new Book("wxq", "142"));
        data2.add(new Book("wxq", "152"));
        data2.add(new Book("wxq", "192"));
        data2.add(new Book("wxq", "202"));

        quickAdapter = new QuickAdapter<Book>(
                this, data, R.layout.book_list_item) {
            @Override
            public void convert(QuickViewHolder helper, Book item,int position) {
                //相当于getview
                helper.setText(R.id.tv_bookname, item.getName());
                helper.setText(R.id.tv_price, item.getPrice());

                Log.d("Main2Activity", "position:" + position);
                if (position == selectPosition) {
                    //
                    System.out.println("1111");
                    Log.d("Main2Activity", "red:" + position);
                    TextView a = (TextView) helper.getView(R.id.tv_bookname);
                    a.setBackgroundResource(R.color.red);
                } else {
                    System.out.println("22222");
                    Log.d("Main2Activity", "white:" + position);
                    TextView a = (TextView) helper.getView(R.id.tv_bookname);
                    a.setBackgroundResource(R.color.white);
                }
                //  notifyDataSetChanged();

            }


        };

        easy_list.setAdapter(quickAdapter);
        //listview刷新时调用

        //listview加载亘多时调用

        easy_list.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("setOnRefreshListener========");
                quickAdapter.loadMore(data2);
                quickAdapter.notifyDataSetChanged();
//                //结束后主线程调用
                easy_list.onRefreshComplete(true);
            }

            @Override
            public void onLoadMore() {
                System.out.println("onLoadMore========");
//                //结束后主线程调用
                quickAdapter.loadMore(data2);//
                easy_list.onRefreshComplete(true);//耗时操作执行完后
            }
        });

        easy_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Main2Activity.this, "你点击了" + i, Toast.LENGTH_SHORT).show();


                selectPosition = i;

                Log.d("Main2Activity", "i:" + i);


                quickAdapter.notifyDataSetChanged();


            }
        });


    }


    class EasyAdapter extends MyBaseAdapter<Book> {


        public EasyAdapter(ArrayList<Book> data) {
            super(data);
        }

        @Override
        public MyBaseHolder<Book> getHolder() {
            easyListHold = new EasyListHold();
            return easyListHold; //上下文的问题
        }

    }


    @Subscribe
    public void eat(String food) {
        //  Toast.makeText(this,food,Toast.LENGTH_SHORT).show();

        btn_next.setText(food);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁
        RxBus.get().unregister(this);
    }
}