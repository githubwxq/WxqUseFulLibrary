package com.example.wxq.wxqusefullibrary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.widget.MyTopViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class viewpageFragment1 extends Fragment {


    @BindView(R.id.viewPager_1)
    MyTopViewPager viewPager1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpagefragment1, null);


        ButterKnife.bind(this, view);

        viewPager1.setAdapter(new PagerAdapter() { @Override
        public int getCount() {                                                                 //获得size
            // TODO Auto-generated method stub
            return 5;
        }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(View view, int position, Object object)                       //销毁Item
            {
               // ((ViewPager) view).removeView(viewLists.get(position));
            }

            @Override
            public Object instantiateItem(View view, int position)                                //实例化Item
            {
               // ((ViewPager) view).addView(viewLists.get(position), 0);
TextView a=new TextView(getActivity());
                a.setText("wxqx");



                return a;
            }
        });

        return view;
    }


}
