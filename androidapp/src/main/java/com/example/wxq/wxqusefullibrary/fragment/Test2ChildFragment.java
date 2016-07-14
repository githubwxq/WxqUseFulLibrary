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
    int mleftPositon;

    ArrayList<String> datas;
    ArrayList<String> datasright;
    RightAdapter mrightadapter;

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
        initRight();
        initLeft();



        return view;
    }

    private void initDatas() {
        datas=new ArrayList<String>();
        datas.add("传递数据1");
        datas.add("传递数据2");
        datas.add("传递数据3");
        datas.add("传递数据4");
        datas.add("传递数据5");
        datas.add("传递数据6");
        datas.add("传递数据7");


        datasright=new ArrayList<String>();
        datasright.add("right1");
        datasright.add("right2");
        datasright.add("right3");
        datasright.add("right4");
        datasright.add("right5");

    }
    private void initDatasRight(String leftdata) {
// 获取新数据
        datasright=new ArrayList<String>();

        if(leftdata.equals("传递数据1")){

            datasright.add("rightaaaa");
            datasright.add("rightbbbb");
            datasright.add("rightcccc");
            datasright.add("rightdddd");
            datasright.add("righteeeee");

        }
         if(leftdata.equals("传递数据2")){

            datasright.add("righta");
            datasright.add("rightb");
            datasright.add("rightc");
            datasright.add("rightd");
            datasright.add("righte");
        }



    }


    private void initRight() {


        //前后顺序
        rlViewRight.setLayoutManager(new LinearLayoutManager(getActivity()));

        rlViewRight.setItemAnimator(new DefaultItemAnimator());

        mrightadapter=new RightAdapter() ;
        rlViewRight.setAdapter(mrightadapter);



    }

    private void initLeft() {

        //前后顺序
        rlViewLeft.setLayoutManager(new LinearLayoutManager(getActivity()));

        rlViewLeft.setItemAnimator(new DefaultItemAnimator());


        rlViewLeft.setAdapter(new LeftAdapter());






    }

    class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyViewHolder>
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
        public void onBindViewHolder(final MyViewHolder holder, final int position)
        {
            holder.tv.setText(datas.get(position));
            if(position==mleftPositon){
                holder.tv.setBackgroundResource(R.color.green);

            }else{
                holder.tv.setBackgroundResource(R.color.white);

            }
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                     mleftPositon=position;
                     initDatasRight(datas.get(position));
                     mrightadapter.notifyDataSetChanged();

                    notifyDataSetChanged();
                }
            });




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


    private class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyRightHolder> {


        @Override
        public MyRightHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyRightHolder holder = new MyRightHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.right_item, parent,
                    false));
            return holder;
        }


        @Override
        public void onBindViewHolder(final MyRightHolder holder, final int position)
        {
            holder.rtv.setText(datasright.get(position));





        }

        @Override
        public int getItemCount()
        {
            return datasright.size();
        }

        class MyRightHolder extends  RecyclerView.ViewHolder
        {

            TextView rtv;
            public MyRightHolder(View view)
            {
                super(view);
                rtv = (TextView) view.findViewById(R.id.tv_rightnumber);
            }
        }
    }
}

