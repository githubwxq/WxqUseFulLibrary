package com.example.wxq.wxqusefullibrary.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
import com.example.wxq.wxqutilslibrary.widget.springview.SpringView;
import com.example.wxq.wxqutilslibrary.widget.springview.headandfoot.RotationFooter;
import com.example.wxq.wxqutilslibrary.widget.springview.headandfoot.RotationHeader;

import java.util.ArrayList;
import java.util.List;

public class SpringListViewActivity extends BaseActivity {

    private SpringView springView;
    private List<String> mDatas = new ArrayList<String>();
    private ListView listView;
    private AdapterForList listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_list_view);
        initData();

        listView = (ListView) findViewById(R.id.list);
        listAdapter = new AdapterForList(this, mDatas);
        listView.setAdapter(listAdapter);

        springView = (SpringView) findViewById(R.id.springview);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        springView.setHeader(new RotationHeader(this));
//       springView.setHeader(null);
        springView.setFooter(new RotationFooter(this));

     //   springView.setGive(SpringView.Give.BOTTOM);
    }

    @Override
    public void widgetClick(View v) {

    }
    private void initData(){
        for (int i = 0; i < 8; i++) {
            mDatas.add(i==0?"We are in ListView":(i==1?"SpringView支持ListView\n\n你可以轻易定制个性化的头部和尾部并在任何控件中使用它":""));
        }
    }


    private class AdapterForList extends BaseAdapter {
        private Context context = null;
        private List<String> results;
        public AdapterForList(Context context,List<String> results){
            this.context = context;
            this.results = results;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return results.get(position);
        }

        @Override
        public int getCount() {
            return results.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item_text;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
                item_text = (TextView)convertView.findViewById(R.id.text);
                convertView.setTag(item_text);
            }else {
                item_text = (TextView) convertView.getTag();
            }
            if (position%2==1) {
                item_text.setBackgroundColor(Color.parseColor("#e3f1fc"));
                item_text.setTextColor(Color.parseColor("#9dd2fc"));
            }
            else {
                item_text.setBackgroundColor(Color.parseColor("#ffffff"));
                item_text.setTextColor(Color.parseColor("#cccccc"));
            }
            item_text.setText(results.get(position));
            return convertView;
        }
    }
}
