package com.example.wxq.wxqusefullibrary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.Test1Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Test2Fragment extends Fragment {
    @BindView(R.id.tv_f2)
    TextView tvF2;
    @BindView(R.id.fg_viewPager)
    ViewPager fgViewPager;
    @BindView(R.id.fg_tablayout)
    TabLayout fgTablayout;
ArrayList<Fragment> flists;
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
        View view = inflater.inflate(R.layout.test2fragment, null);


        ButterKnife.bind(this, view);
        flists=new ArrayList<Fragment>();
        flists.add(new Test1Fragment());
        flists.add(new Test5Fragment());

        tvF2.setText("framgment2");

        fgViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return flists.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "子"+position+1+"个";
                    case 1:
                        return "子"+position+1+"个";

                    default:
                        return "第"+position+1+"个";
                }
            }
        });
        fgTablayout.setupWithViewPager(fgViewPager);

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
