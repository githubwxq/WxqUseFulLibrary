package com.example.wxq.wxqusefullibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxq.wxqusefullibrary.Book;
import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqutilslibrary.widget.adapter.QuickAdapter;
import com.example.wxq.wxqutilslibrary.widget.adapter.QuickViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 * //测试封装的listviewadpter
 */
public class Test5Fragment extends Fragment {
    @BindView(R.id.tv_f5)
    TextView tvF5;
    @BindView(R.id.lv_fg5)
    ListView lvFg5;
ArrayList<Book> data;
    ArrayList<Book> moredata;
    private int selectPostion;
    QuickAdapter<Book> quickAdapter;
    //ctrl +alt +k 生命周期排序
    @Override
    public void onAttach(Context context) {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreate...");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreate...");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onCreateView...");
        View view = inflater.inflate(R.layout.test5fragment, null);


        ButterKnife.bind(this, view);
        data=new ArrayList<Book>();
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

        moredata=new ArrayList<Book>();
        moredata.add(new Book("moredatawxq", "14"));
        moredata.add(new Book("moredatawxq", "15"));
        moredata.add(new Book("moredatawxq", "19"));
        moredata.add(new Book("moredatawxq", "20"));
        moredata.add(new Book("moredatawxq", "21"));


        lvFg5.setAdapter(quickAdapter=new QuickAdapter<Book>( getActivity(), data, R.layout.book_list_item) {
            @Override
            public void convert(QuickViewHolder helper, Book item) {
                int currentPositon = helper.getPosition();

                helper.setText(R.id.tv_bookname,item.getName());
                helper.setText(R.id.tv_price, item.getPrice());
                Toast.makeText(getActivity(), "currentPositon:" + currentPositon, Toast.LENGTH_SHORT).show();
if(currentPositon==selectPostion){
    helper.setText(R.id.tv_bookname,"我被点击了位子为"+selectPostion);

}else {


}

            }
        });
        lvFg5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "当前点击位置:" + i, Toast.LENGTH_SHORT).show();
                selectPostion=i;
                quickAdapter.notifyDataSetChanged();
            }
        });

     tvF5.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

            data.addAll(moredata);
             quickAdapter.notifyDataSetChanged();
         }
     });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(this.getClass().getName(), "ArrayListFragment **** onActivityCreated...");

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onStart...");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onResume...");
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onPause...");
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onStop...");
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDestroyView...");
        // TODO Auto-generated method stub
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDestroy...");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.e(this.getClass().getName(), "ArrayListFragment **** onDetach...");
        // TODO Auto-generated method stub
        super.onDetach();
    }


}

