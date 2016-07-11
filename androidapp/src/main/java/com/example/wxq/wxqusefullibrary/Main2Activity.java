package com.example.wxq.wxqusefullibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.wxq.wxqutilslibrary.widget.adapter.MyBaseAdapter;
import com.example.wxq.wxqutilslibrary.widget.adapter.MyBaseHolder;
import com.example.wxq.wxqutilslibrary.widget.listview.RefreshListView;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    RefreshListView easy_list;
    Button btn_next;
    EasyListHold easyListHold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RxBus.get().register(this);

        btn_next= (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, MyRxActivity.class);
                startActivity(intent);
            }
        });
        easyListHold=new EasyListHold(this);
        easy_list= (RefreshListView) findViewById(R.id.easy_list);
        final ArrayList<Book> data=new ArrayList<>();
        data.add(new Book("wxq","14"));
        data.add(new Book("wxq","15"));
        data.add(new Book("wxq","19"));
        data.add(new Book("wxq","20"));
        data.add(new Book("wxq","21"));
        data.add(new Book("wxq","22"));
        data.add(new Book("wxq","23"));
        data.add(new Book("wxq","24"));
        data.add(new Book("wxq","25"));
        data.add(new Book("wxq","56"));
        data.add(new Book("wxq","24"));

        final ArrayList<Book> data2=new ArrayList<>();
        data2.add(new Book("wxq","142"));
        data2.add(new Book("wxq","152"));
        data2.add(new Book("wxq","192"));
        data2.add(new Book("wxq","202"));

        final EasyAdapter easyAdapter = new EasyAdapter(data);

        easy_list.setAdapter(easyAdapter);
      //listview刷新时调用

        //listview加载亘多时调用

        easy_list.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                easyAdapter.refreshNewData(data);
                //结束后主线程调用
                easy_list.onRefreshComplete(true);
            }

            @Override
            public void onLoadMore() {
                //结束后主线程调用
                easyAdapter.loadMore(data2);
              easy_list.onRefreshComplete(true);
            }
        });

        easy_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Main2Activity.this, "你点击了"+ i, Toast.LENGTH_SHORT).show();
                easyListHold.setSelectPositon(i);
                easy_list.setAdapter(easyAdapter);





          //      easyAdapter.notifyDataSetChanged();

            }
        });



    }



    class EasyAdapter extends MyBaseAdapter<Book>{


        public EasyAdapter(ArrayList<Book> data) {
            super(data);
        }

        @Override
        public MyBaseHolder<Book> getHolder() {

          easyListHold = new EasyListHold(Main2Activity.this);
            return easyListHold; //上下文的问题
        }
    }


    @Subscribe
    public void eat(String food) {
    //    Toast.makeText(this,food,Toast.LENGTH_SHORT).show();

        btn_next.setText(food);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁
        RxBus.get().unregister(this);
    }
}
