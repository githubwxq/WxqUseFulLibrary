package com.example.wxq.wxqusefullibrary.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.model.Book;
import com.example.wxq.wxqusefullibrary.widget.MyTopViewPager;
import com.example.wxq.wxqutilslibrary.widget.adapter.QuickPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/12.
 */
public class viewpageFragment1 extends Fragment {


    @BindView(R.id.viewPager_1)
    MyTopViewPager viewPager1;
    ArrayList<Book> books;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpagefragment1, null);
        books = new ArrayList<Book>();
        Book book1 = new Book("1", "100");
        Book book2 = new Book("2", "200");
        Book book3 = new Book("3", "300");
        Book book4 = new Book("4", "400");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        ButterKnife.bind(this, view);


        viewPager1.setAdapter(new QuickPageAdapter<Book>(R.layout.book_list_item, getActivity(), books) {

            @Override
            public void setData(View view, int position, Book book) {

                TextView viewById = (TextView) view.findViewById(R.id.tv_bookname);
                viewById.setText("viewpage:" + book.getName());

                TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
                tv_price.setText("viewpage:" + book.getPrice());


            }
        });

//        viewPager1.setAdapter(new PagerAdapter() {
//            @Override
//        public int getCount() {                                                                 //获得size
//            // TODO Auto-generated method stub
//            return 5;
//        }
//
//            @Override
//            public boolean isViewFromObject(View arg0, Object arg1) {
//                // TODO Auto-generated method stub
//                return arg0 == arg1;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                //  super.destroyItem(container, position, object);
//                container.removeView((View) object);
//            }
//
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//
//                TextView tv=new TextView(getActivity());
//                tv.setText("viewpage1");
//
//
//
//                container.addView(tv);
//                return tv;
//            }
//        });

        return view;
    }


}
