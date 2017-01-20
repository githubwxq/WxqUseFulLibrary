package recordingvoice;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wxq.wxqutilslibrary.R;

/**
     * @description 对话框管理工具类
     * @author ldm
     * @time  2016/6/25 11:53
     * @param
  */
public class DialogManager {
   //弹出对话框
   private Dialog mDialog;
   //录音图标
   private ImageView mIcon;
   //音量显示 图标
   private ImageView mVoice;
   //对话框上提示文字
   private TextView mLable;
   //上下文对象
   private Context mContext;


   public DialogManager(Context context) {
       this.mContext = context;
   }

   /**
    * @param
    * @description 显示对话框
    * @author ldm
    * @time 2016/6/25 9:56
    */
   public void showRecordingDialog() {
       //根据指定sytle实例化Dialog
       mDialog = new Dialog(mContext, R.style.AudioDialog);
       LayoutInflater inflater = LayoutInflater.from(mContext);
       View view = inflater.inflate(R.layout.dialog_recorder, null);
       mDialog.setContentView(view);
       mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
       mIcon = (ImageView) view.findViewById(R.id.id_recorder_dialog_icon);
       mVoice = (ImageView) view.findViewById(R.id.id_recorder_dialog_voice);
       mLable = (TextView) view.findViewById(R.id.id_recorder_dialog_label);
       mDialog.show();
   }

   /**
    * @param
    * @description 正在录音状态的对话框
    * @author ldm
    * @time 2016/6/25 10:08
    */
   public void recording() {
       if (mDialog != null && mDialog.isShowing()) {
           mIcon.setVisibility(View.VISIBLE);
           mVoice.setVisibility(View.VISIBLE);
           mLable.setVisibility(View.VISIBLE);
           mIcon.setImageResource(R.drawable.recorder);
           mLable.setText("手指上滑，取消发送");
       }
   }

   /**
    * @param
    * @description 取消录音状态对话框
    * @author ldm
    * @time 2016/6/25 10:08
    */
   public void wantToCancel() {
       if (mDialog != null && mDialog.isShowing()) {
           mIcon.setVisibility(View.VISIBLE);
           mVoice.setVisibility(View.GONE);
           mLable.setVisibility(View.VISIBLE);
           mIcon.setImageResource(R.mipmap.cancel);
           mLable.setText("松开手指，取消发送");
       }
   }

   /**
    * @param
    * @description时间过短提示的对话框
    * @author ldm
    * @time 2016/6/25 10:09
    */
   public void tooShort() {
       if (mDialog != null && mDialog.isShowing()) { //显示状态
           mIcon.setVisibility(View.VISIBLE);
           mVoice.setVisibility(View.GONE);
           mLable.setVisibility(View.VISIBLE);
           mIcon.setImageResource(R.mipmap.voice_to_short);
           mLable.setText("录音时间过短");
       }
   }

   /**
    * @param
    * @description
    * @author ldm
    * @time 2016/6/25 取消（关闭）对话框
    */
   public void dimissDialog() {
       if (mDialog != null && mDialog.isShowing()) { //显示状态
           mDialog.dismiss();
           mDialog = null;
       }
   }

   // 显示更新音量级别的对话框
   public void updateVoiceLevel(int level) {
       if (mDialog != null && mDialog.isShowing()) { //显示状态
           mIcon.setVisibility(View.VISIBLE);
           mVoice.setVisibility(View.VISIBLE);
           mLable.setVisibility(View.VISIBLE);
           //设置图片的id，我们放在drawable中的声音图片是以v+数字格式的
           int resId = mContext.getResources().getIdentifier("v" + level, "mipmap", mContext.getPackageName());
           mVoice.setImageResource(resId);
       }
   }

}
