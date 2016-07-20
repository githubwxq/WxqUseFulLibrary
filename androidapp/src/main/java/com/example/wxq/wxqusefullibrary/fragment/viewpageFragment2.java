package com.example.wxq.wxqusefullibrary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class viewpageFragment2 extends Fragment {


    @BindView(R.id.viewpage_list)
    ListView viewpageList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpagefragment2, null);


        ButterKnife.bind(this, view);
        viewpageList.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 15;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                TextView tv=new TextView(getContext());
//                LinearLayout.LayoutParams a=new LinearLayout.LayoutParams(240,240);
//                tv.setLayoutParams(a);
                tv.setText("qqqqqqqqqqqq");

                return tv;
            }
        });
//View head=View.inflate(getContext(),R.layout.viewpagehead,null);
//       ViewPager headviewpage= (ViewPager) head.findViewById(R.id.viewpage_head);
//        headviewpage.setAdapter(new PagerAdapter() {
//
//
//            @Override
//            public int getCount() {
//                // TODO Auto-generated method stub
//                return 5;
//            }
//
//            @Override
//            public boolean isViewFromObject(View arg0, Object arg1) {
//                // TODO Auto-generated method stub
//                return false;
//            }
//
//
//            @Override
//            public void destroyItem(View container, int position, Object object) {
//                // TODO Auto-generated method stub
//                super.destroyItem(container, position, object);
//            }
//
//            @Override
//            public Object instantiateItem(View container, int position) {
//                // TODO Auto-generated method stub
//                TextView tv=new TextView(getContext());
//                LinearLayout.LayoutParams a=new LinearLayout.LayoutParams(240,240);
//                tv.setLayoutParams(a);
//                tv.setText("viewpage");
//                ((ViewPager) container).addView(tv);
//                return tv;
//               // return super.instantiateItem(container, position);
//            }
//
//        });
//
//        viewpageList.addHeaderView(head);




        return view;
    }


}
