package com.example.wxq.wxqusefullibrary.bmob.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.activity.MyApplication;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.MyUser;
import com.example.wxq.wxqusefullibrary.model.User;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import edittext.DeletableEditText;
import imageview.BitmapUtil;
import imageview.CircleImageView;

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private static final int REGISTER_REQUEST_CODE = 100;
    private static final int REGISTER_RESULT_CODE =  200 ;
    final float radius = 8;
    final double scaleFactor = 10;
    private CircleImageView userIcon;
    private DeletableEditText userNameInput;
    private DeletableEditText userPwdInput;
    private Button loginButton;
    private TextView registerLink;
    private ImageView backgroundImage;
    private Uri background = null;
    private Context context;
    private User user;            // 根据账户名查询的用户
    // private UserProxy userProxy ;  // 用户操作代理
    private String userNameStr = null;
    private Animation animation; // 登录icon 旋转 动画

    //  private YmApplication ymApplication ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitleHeadVisiable(false);
        initView();

    }

    private void initView() {
        context=this;
        userIcon = (CircleImageView) findViewById(R.id.userIcon);
        userNameInput = (DeletableEditText) findViewById(R.id.user_name_input);
        userPwdInput = (DeletableEditText) findViewById(R.id.user_pwd_input);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerLink = (TextView) findViewById(R.id.register_link);
        backgroundImage = (ImageView) findViewById(R.id.login_backgroundImage);
        BitmapDrawable bitmapDrawable;
        bitmapDrawable = BitmapUtil.createBlur(getResources(), R.drawable.entrance1, MyApplication.getInstance().mContext);
        backgroundImage.setImageDrawable(bitmapDrawable);
        animation = AnimationUtils.loadAnimation(this, R.anim.login_icon);
        LinearInterpolator lin = new LinearInterpolator(); // 匀速旋转
        animation.setInterpolator(lin);

        loginButton.setOnClickListener(this);
        //   userPwdInput.setOnFocusChangeListener(userPwdFocus);
        // userProxy.setOnLoginListener(this);
        registerLink.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                login();
                break;
            case R.id.register_link:
                register();
                break;
        }
    }

    private void register() {
        Intent intent = new Intent() ;
         intent.setClass(this,RegisterActivity.class) ;
            /*if (background != null) {
                intent.putExtra("background", background);
            }*/
        //startActivity(intent);
        startActivityForResult(intent, REGISTER_REQUEST_CODE);
    }

    private void login() {
        String name = userNameInput.getText().toString();
        String password = userPwdInput.getText().toString();

        if (name.isEmpty() && password.isEmpty()) {
            userNameInput.setShakeAnimation();
            userPwdInput.setShakeAnimation();
            //Toast.makeText(LoginActivity.this,"请输入账户信息",Toast.LENGTH_SHORT).show();
            showToast("请输入账户信息");
            return;
        }

        if (name.isEmpty()) {
            userNameInput.setShakeAnimation();
            //    Toast.makeText(LoginActivity.this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
            showToast("用户名不能为空");
            return;
        }

        if (password.isEmpty()) {
            userPwdInput.setShakeAnimation();
            //    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            showToast("密码不能为空");
            return;
        }

        //  开始登录动画
        if (animation != null) {
            userIcon.startAnimation(animation);
        }



        MyUser user = new MyUser();
        user.setUsername(name);
        user.setPassword(password);
        user.login(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                    startActivity(new Intent(LoginActivity.this, BmobIndexActivity.class));
                    finish();
                }else{
                    showToast("用户名或密码出错！");
                   // userIcon.startAnimation(animation);
                    userIcon.clearAnimation();
                }
            }
        });


//        userProxy.login(name,SHA_password);  // 登录
//        //登录时设置不可点击
//        registerLink.setClickable(false);
//        loginButton.setClickable(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){ // 结果数
            case REGISTER_RESULT_CODE:
                String name = data.getStringExtra("username") ;
                String password = data.getStringExtra("password") ;
                userNameInput.setText(name);
                userPwdInput.setText(password);
                break ;
        }
    }
    /**
     * 登录按钮状态
     */
    public void updateLoginButton(){
        if (userNameInput.getText().length() == 0) {
            loginButton.setEnabled(false);
            return;
        }
        if (userPwdInput.getText().length() == 0) {
            loginButton.setEnabled(false);
            return;
        }
        loginButton.setEnabled(true);
    }


}
