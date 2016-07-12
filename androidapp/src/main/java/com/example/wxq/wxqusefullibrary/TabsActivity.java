package com.example.wxq.wxqusefullibrary;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class TabsActivity extends AppCompatActivity {

    TabLayout tabs;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);
        tabs= (TabLayout) findViewById(R.id.tablayout);

        viewPager= (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
          @Override
          public Fragment getItem(int position) {
              //吧fragment 放到集合里面
              switch (position) {
                  case 0:
                      return new Test1Fragment();
                  case 1:
                      return new Test1Fragment();
                  case 2:
                      return new Test1Fragment();
                  case 3:
                      return new Test1Fragment();
                  case 4:
                      return new Test1Fragment();
                  case 5:
                      return new Test1Fragment();
                  default:
                      return new Test1Fragment();
              }

          }

          @Override
          public CharSequence getPageTitle(int position) {
              switch (position) {
                  case 0:
                      return "第"+position+1+"个";
                  case 1:
                      return "第"+position+1+"个";
                  case 2:
                      return "第"+position+1+"个";
                  case 3:
                      return "第"+position+1+"个";
                  case 4:
                      return "第"+position+1+"个";
                  case 5:
                      return "第"+position+1+"个";
                  default:
                      return "第"+position+1+"个";
              }
          }

          @Override
          public int getCount() {
              return 5;
          }
      });
        tabs.setupWithViewPager(viewPager);

    }


}
