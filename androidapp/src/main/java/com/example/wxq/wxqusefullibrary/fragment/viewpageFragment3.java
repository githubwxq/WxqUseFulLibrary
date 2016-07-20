package com.example.wxq.wxqusefullibrary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class viewpageFragment3 extends Fragment {


    @BindView(R.id.viewpage_2)
    ViewPager viewpage2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpagefragment3, null);

        ButterKnife.bind(this, view);
        viewpage2.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                TextView tv=new TextView(getActivity());
                tv.setText("qweqwe");
                container.addView(tv);
                return tv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
              //  super.destroyItem(container, position, object);
                  container.removeView((View) object);
            }
        });

        return view;
    }


}
