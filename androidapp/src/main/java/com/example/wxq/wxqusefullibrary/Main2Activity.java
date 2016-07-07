package com.example.wxq.wxqusefullibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.wxq.wxqutilslibrary.widget.adapter.MyBaseAdapter;
import com.example.wxq.wxqutilslibrary.widget.adapter.MyBaseHolder;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
   ListView  easy_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        easy_list= (ListView) findViewById(R.id.easy_list);
        ArrayList<Book> data=new ArrayList<>();
        data.add(new Book("wxq","14"));
        data.add(new Book("wxq","15"));
        data.add(new Book("wxq","19"));
        data.add(new Book("wxq","20"));
        easy_list.setAdapter(new EasyAdapter(data));





    }

    class EasyAdapter extends MyBaseAdapter<Book>{


        public EasyAdapter(ArrayList<Book> data) {
            super(data);
        }

        @Override
        public MyBaseHolder<Book> getHolder() {
            return new EasyListHold(Main2Activity.this);
        }
    }
}
