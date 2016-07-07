package com.example.wxq.wxqusefullibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wxq.wxqutilslibrary.widget.dialog.BottomView;

import Tools.ScreenUtils;

public class MainActivity extends AppCompatActivity {
BottomView bottomView=null;
 //CommonPopupWindow popupWindow;
    TextView tvHellow;

    TextView tvWxq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //  popupWindow= new CommonPopupWindow();

        bottomView=new BottomView(this, R.style.BottomViewTheme_Defalut, R.layout.activity_dialog);

        bottomView.setTop(false);
        bottomView.showBottomView(true);
        bottomView.dismissBottomView();
        setContentView(R.layout.activity_main);
        tvHellow= (TextView) findViewById(R.id.tv_hellow);


        tvWxq= (TextView) findViewById(R.id.tv_wxq);
        tvWxq.setText(ScreenUtils.getScreenWidth(this)+"");
        tvWxq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomView.dismissBottomView();
                Intent a=new Intent(MainActivity.this,Main2Activity.class);

                startActivity(a);

            //   popupWindow.disspop();
            }
        });
       // CommonDialogUtils.showDailog(this,R.layout.activity_dialog);





       // popupWindow.easyPopupWindow(this,tvHellow,R.layout.activity_dialog);

    }


}
