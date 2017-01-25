package com.example.wxq.wxqusefullibrary.bmob.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.activity.MyApplication;
import com.example.wxq.wxqusefullibrary.bmob.activity.model.MyUser;
import com.example.wxq.wxqutilslibrary.activity.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import edittext.DeletableEditText;
import imageview.BitmapUtil;

public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity" ;
    RelativeLayout rl_bg;
    private DeletableEditText userNameInput;
    private DeletableEditText userPwdInput,reUserPwdInput;
    private Button registerButton;
    private TextView loginLink;
    private ImageView backgroundImage;
    private Uri background = null;
    private Context context ;
    private String name,password,repassword ;
 //   private UserProxy userProxy ;  // 用户操作代理

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitleHeadVisiable(false);
        context = this ;
        initView();
        initListener();

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.login_link:
                loginLink();
                break;

            case R.id.registerButton:
                registerButton();
                break;
        }
    }

    private void registerButton() {
        name = userNameInput.getText().toString();
        password = userPwdInput.getText().toString();
        repassword = reUserPwdInput.getText().toString();
        if(name.isEmpty() && password.isEmpty() && repassword.isEmpty()){
            userNameInput.setShakeAnimation();
            userPwdInput.setShakeAnimation();
            reUserPwdInput.setShakeAnimation();
            //   Toast.makeText(context, "请输入注册信息", Toast.LENGTH_SHORT).show();
         showToast("请输入注册信息");
            return ;
        }
        if (name.isEmpty()) {
            userNameInput.setShakeAnimation();
            //    Toast.makeText(context,"邮箱不能为空",Toast.LENGTH_SHORT).show();
            showToast("账号不能为空");
            return;
        }
        if (password.isEmpty()) {
            userPwdInput.setShakeAnimation();
            //   Toast.makeText(context,"密码不能为空",Toast.LENGTH_SHORT).show();
       showToast("密码不能为空");
            return;
        }
        if (repassword.isEmpty()) {
            reUserPwdInput.setShakeAnimation();
            //    Toast.makeText(context,"确认密码不能为空",Toast.LENGTH_SHORT).show();
           showToast("确认密码不能为空");
            return;
        }
//        if(!TextUtil.isEmail(name)){
//            userNameInput.setShakeAnimation();
//            //    Toast.makeText(context,"邮箱格式错误",Toast.LENGTH_SHORT).show();
//            ToastView.showToast(context, "邮箱格式错误", Toast.LENGTH_SHORT);
//            return;
//        }
        if(!password.equals(repassword)){
            reUserPwdInput.setShakeAnimation();
            //   Toast.makeText(context,"密码不一致",Toast.LENGTH_SHORT).show();
         showToast("密码不一致");
            return;
        }

//        // 加密
//        String SHA_password = EncryptUtil.SHA1(password.trim()) ;
//        LogUtil.d(TAG,"-------------SHA_password="+SHA_password);
//        userProxy.signUp(name.trim(),SHA_password);
//        loginLink.setClickable(false);
//        registerButton.setClickable(false);
        /**
         * @param email
         * @param password 注册
         */
        MyUser user = new MyUser();
        user.setPassword(password);
        user.setUsername(name);
//        user.setEmail(email);
//        user.setUsername(email);
        //   String str[] = email.split("@") ;
        //   user.setNickname(str[0]);  //  邮箱前缀 eg：xxx@qq.com    xxx
     //   user.setNickname(email);
        user.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser user, BmobException e) {
                if (e==null) {
                    showToast("成功注册"+user.getUsername());
                    Intent intent = new Intent() ;
                    intent.setClass(context,LoginActivity.class) ;
                    intent.putExtra("username",name.trim()) ;
                    intent.putExtra("password",password.trim()) ;
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    setResult(200,intent);
                    finish();
                }else{
                    showToast("注册失败"+e.toString());
                }

            }
        });

    }

    private void loginLink() {
        Intent intent = new Intent() ;
        intent.setClass(context,LoginActivity.class) ;
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    /**
     * 初始化控件
     */
    public void initView(){

        userNameInput = (DeletableEditText) findViewById(R.id.user_name);
        userPwdInput = (DeletableEditText) findViewById(R.id.user_pwd);
        reUserPwdInput = (DeletableEditText) findViewById(R.id.re_user_pwd);
        registerButton = (Button) findViewById(R.id.registerButton);
        loginLink = (TextView) findViewById(R.id.login_link);
        backgroundImage = (ImageView) findViewById(R.id.register_backgroundImage);
        BitmapDrawable bitmapDrawable;
         bitmapDrawable = BitmapUtil.createBlur(getResources(),R.drawable.entrance1, MyApplication.getInstance().mContext);
        backgroundImage.setImageDrawable(bitmapDrawable);
    }
    /**
     * 注册监听
     */
    public void initListener(){
        loginLink.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    //    userProxy.setOnSignUpListener(this);
    }

    public static Bitmap convertToBlur(Bitmap bmp) {
        // 高斯矩阵
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap newBmp = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 16; // 值越小图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + pixR * gauss[idx];
                        newG = newG + pixG * gauss[idx];
                        newB = newB + pixB * gauss[idx];
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);

        return newBmp;
    }


}
