package com.example.wxq.wxqusefullibrary.bmob.activity.homepage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.bmob.activity.homepage.childfragment.BmobDataConectionFragment;
import com.example.wxq.wxqusefullibrary.bmob.activity.homepage.childfragment.RecommendFragment;
import com.example.wxq.wxqusefullibrary.fragment.lazyFragment2;
import com.example.wxq.wxqusefullibrary.fragment.lazyFragment3;
import com.example.wxq.wxqusefullibrary.fragment.lazyFragment4;
import com.example.wxq.wxqutilslibrary.fragment.SuperFragment;
import com.example.wxq.wxqutilslibrary.widget.mulripletablayout.SlidingTabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends SuperFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    protected int getResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initDataAndView(View view) {
        /**自定义部分属性*/
        mFragments.add(BmobDataConectionFragment.newInstance());
        mFragments.add(RecommendFragment.newInstance());
        mFragments.add(new lazyFragment3());
        mFragments.add(new lazyFragment2());
        mFragments.add(new lazyFragment3());
        mFragments.add(new lazyFragment4());
        SlidingTabLayout tabLayout_2 = (SlidingTabLayout) view.findViewById(R.id.tl_2);
        ViewPager vp = (ViewPager) view.findViewById(R.id.vp);
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(mAdapter);
        tabLayout_2.setViewPager(vp);




    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
    private final String[] mTitles = {
            "bombdataconnection", "recommendfragment", "edittext"
            , "button", "viewpage", "toast"
    };
    private MyPagerAdapter mAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    //普通加载
    @Override
    public void commonLoad(View view) {

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
