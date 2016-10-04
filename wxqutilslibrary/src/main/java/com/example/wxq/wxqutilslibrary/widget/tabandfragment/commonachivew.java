package com.example.wxq.wxqutilslibrary.widget.tabandfragment;

/**
 * Created by Administrator on 2016/10/2.
 * 最老旧的方法实现tab和fragment 不过确实另类复杂就用这个吧！
 */
public class commonachivew {
//    package com.imooc.tab03;
//
//    import java.util.ArrayList;
//    import java.util.List;
//
//    import android.os.Bundle;
//    import android.support.v4.app.Fragment;
//    import android.support.v4.app.FragmentActivity;
//    import android.support.v4.app.FragmentPagerAdapter;
//    import android.support.v4.view.ViewPager;
//    import android.support.v4.view.ViewPager.OnPageChangeListener;
//    import android.view.View;
//    import android.view.View.OnClickListener;
//    import android.view.Window;
//    import android.widget.ImageButton;
//    import android.widget.LinearLayout;
//
//    public class MainActivity extends FragmentActivity implements OnClickListener
//    {
//        private ViewPager mViewPager;
//        private FragmentPagerAdapter mAdapter;
//        private List<Fragment> mFragments;
//
//        private LinearLayout mTabWeixin;
//        private LinearLayout mTabFrd;
//        private LinearLayout mTabAddress;
//        private LinearLayout mTabSettings;
//
//        private ImageButton mImgWeixin;
//        private ImageButton mImgFrd;
//        private ImageButton mImgAddress;
//        private ImageButton mImgSettings;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState)
//        {
//            super.onCreate(savedInstanceState);
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            setContentView(R.layout.activity_main);
//
//            initView();
//            initEvent();
//
//            setSelect(1);
//        }
//
//        private void initEvent()
//        {
//            mTabWeixin.setOnClickListener(this);
//            mTabFrd.setOnClickListener(this);
//            mTabAddress.setOnClickListener(this);
//            mTabSettings.setOnClickListener(this);
//        }
//
//        private void initView()
//        {
//            mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
//
//            mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
//            mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
//            mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
//            mTabSettings = (LinearLayout) findViewById(R.id.id_tab_settings);
//
//            mImgWeixin = (ImageButton) findViewById(R.id.id_tab_weixin_img);
//            mImgFrd = (ImageButton) findViewById(R.id.id_tab_frd_img);
//            mImgAddress = (ImageButton) findViewById(R.id.id_tab_address_img);
//            mImgSettings = (ImageButton) findViewById(R.id.id_tab_settings_img);
//
//            mFragments = new ArrayList<Fragment>();
//            Fragment mTab01 = new WeixinFragment();
//            Fragment mTab02 = new FrdFragment();
//            Fragment mTab03 = new AddressFragment();
//            Fragment mTab04 = new SettingFragment();
//            mFragments.add(mTab01);
//            mFragments.add(mTab02);
//            mFragments.add(mTab03);
//            mFragments.add(mTab04);
//
//            mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
//            {
//
//                @Override
//                public int getCount()
//                {
//                    return mFragments.size();
//                }
//
//                @Override
//                public Fragment getItem(int arg0)
//                {
//                    return mFragments.get(arg0);
//                }
//            };
//            mViewPager.setAdapter(mAdapter);
//
//            mViewPager.setOnPageChangeListener(new OnPageChangeListener()
//            {
//
//                @Override
//                public void onPageSelected(int arg0)
//                {
//                    int currentItem = mViewPager.getCurrentItem();
//                    setTab(currentItem);
//                }
//
//                @Override
//                public void onPageScrolled(int arg0, float arg1, int arg2)
//                {
//                    // TODO Auto-generated method stub
//
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int arg0)
//                {
//                    // TODO Auto-generated method stub
//
//                }
//            });
//        }
//
//        @Override
//        public void onClick(View v)
//        {
//            switch (v.getId())
//            {
//                case R.id.id_tab_weixin:
//                    setSelect(0);
//                    break;
//                case R.id.id_tab_frd:
//                    setSelect(1);
//                    break;
//                case R.id.id_tab_address:
//                    setSelect(2);
//                    break;
//                case R.id.id_tab_settings:
//                    setSelect(3);
//                    break;
//
//                default:
//                    break;
//            }
//        }
//
//        private void setSelect(int i)
//        {
//            setTab(i);
//            mViewPager.setCurrentItem(i);
//        }
//
//        private void setTab(int i)
//        {
//            resetImgs();
//            // 设置图片为亮色
//            // 切换内容区域
//            switch (i)
//            {
//                case 0:
//                    mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
//                    break;
//                case 1:
//                    mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
//                    break;
//                case 2:
//                    mImgAddress.setImageResource(R.drawable.tab_address_pressed);
//                    break;
//                case 3:
//                    mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
//                    break;
//            }
//        }
//
//        /**
//         * 切换图片至暗色
//         */
//        private void resetImgs()
//        {
//            mImgWeixin.setImageResource(R.drawable.tab_weixin_normal);
//            mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
//            mImgAddress.setImageResource(R.drawable.tab_address_normal);
//            mImgSettings.setImageResource(R.drawable.tab_settings_normal);
//        }
//
//    }
//
//



}
