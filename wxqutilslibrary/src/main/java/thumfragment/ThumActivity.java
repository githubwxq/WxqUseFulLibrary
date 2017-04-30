//package thumfragment;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.example.wxq.wxqutilslibrary.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ThumActivity extends AppCompatActivity {
//
//    private List<Fragment> fragmentList = new ArrayList<>();
//
//    private ThumbnailMenu thumMenu;
//
//    private ImageView ivMenu;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        findViewById();
//
//        initFragment();
//
//        setViewListener();
//    }
//
//    private void findViewById() {
//        thumMenu = (ThumbnailMenu) findViewById(R.id.thumb);
//        ivMenu = (ImageView) findViewById(R.id.iv_menu);
//    }
//
//    private void initFragment() {
//        Fragment1 fragment1 = new Fragment1();
//        Fragment2 fragment2 = new Fragment2();
//        Fragment3 fragment3 = new Fragment3();
//        Fragment4 fragment4 = new Fragment4();
//        Fragment5 fragment5 = new Fragment5();
//
//        fragmentList.add(fragment5);
//        fragmentList.add(fragment4);
//        fragmentList.add(fragment3);
//        fragmentList.add(fragment2);
//        fragmentList.add(fragment1);
//
//        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return fragmentList.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return fragmentList.size();
//            }
//
//        };
//        thumMenu.setAdapter(adapter);
//    }
//
//    private void setViewListener() {
//        ivMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ivMenu.setVisibility(View.GONE);
//                thumMenu.openMenu();
//            }
//        });
//
//        thumMenu.setOnMenuCloseListener(new ThumbnailMenu.OnThumbnailMenuCloseListener() {
//            @Override
//            public void onMenuCloseListener() {
//                ivMenu.setVisibility(View.VISIBLE);
//            }
//        });
//    }
//
//}


//布局
//
//<?xml version="1.0" encoding="utf-8"?>
//<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        xmlns:app="http://schemas.android.com/apk/res-auto"
//        xmlns:tools="http://schemas.android.com/tools"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        tools:context="com.hitomi.thumbnailmenu.MainActivity">
//
//<com.hitomi.tmlibrary.ThumbnailMenu
//        android:id="@+id/thumb"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        app:menu_direction="bottom"
//        app:scale_ratio=".38">
//
//<RelativeLayout
//android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:background="@mipmap/profile_background">
//<ImageView
//android:layout_width="50dp"
//        android:layout_height="50dp"
//        android:background="@mipmap/bg_fragment_4"
//        android:layout_alignParentRight="true"
//        />
//
//
//
//
//
//</RelativeLayout>
//
//</com.hitomi.tmlibrary.ThumbnailMenu>
//
//<ImageView
//android:id="@+id/iv_menu"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_alignParentRight="true"
//        android:layout_gravity="right"
//        android:layout_marginRight="20dp"
//        android:layout_marginTop="20dp"
//        android:src="@mipmap/ic_hamburg" />
//
//</FrameLayout>
