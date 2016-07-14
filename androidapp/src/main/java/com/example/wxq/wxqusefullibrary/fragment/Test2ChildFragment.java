package com.example.wxq.wxqusefullibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Test2ChildFragment extends Fragment {


    @BindView(R.id.rl_view_left)
    RecyclerView rlViewLeft;
    @BindView(R.id.rl_view_right)
    RecyclerView rlViewRight;

    ArrayList<String> datas;
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
        View view = inflater.inflate(R.layout.test2childfragment, null);

        ButterKnife.bind(this, view);
        initDatas();
        initLeft();

        initRight();

        return view;
    }

    private void initDatas() {
        datas=new ArrayList<String>();
        datas.add("1");
        datas.add("2");
        datas.add("3");
        datas.add("4");
        datas.add("5");
        datas.add("6");
        datas.add("7");

    }

    private void initRight() {
    }

    private void initLeft() {

        //前后顺序
        rlViewLeft.setLayoutManager(new LinearLayoutManager(getActivity()));

        rlViewLeft.setItemAnimator(new DefaultItemAnimator());


        rlViewLeft.setAdapter(new HomeAdapter());





    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.left_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(datas.get(position));
        }

        @Override
        public int getItemCount()
        {
            return datas.size();
        }

        class MyViewHolder extends
                RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv_number);
            }
        }
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

