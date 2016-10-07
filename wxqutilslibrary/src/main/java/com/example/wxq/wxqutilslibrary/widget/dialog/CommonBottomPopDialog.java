package com.example.wxq.wxqutilslibrary.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.wxq.wxqutilslibrary.R;

/**
 * Created by Administrator on 2016/10/7.
 */
public class CommonBottomPopDialog extends Dialog {
    private Context context;
    private Button btn_delete;
    private Button btn_cancel;

    public void setListenerInterface(ClickListenerInterface listenerInterface) {
        this.listenerInterface = listenerInterface;
    }

    private ClickListenerInterface listenerInterface;

    public CommonBottomPopDialog(Context context) {
        super(context, R.style.chooseDialog); //从底部弹出style
        this.context = context;
        init();
    }


    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.commentpopmenu, null);
        Button btn_delete= (Button) view.findViewById(R.id.btn_delete);
        Button btn_cancel= (Button) view.findViewById(R.id.btn_cancel);
        btn_delete.setOnClickListener(new clickListener());
        btn_cancel.setOnClickListener(new clickListener());


        setContentView(view);
        setCanceledOnTouchOutside(true);


        //以下是居中样式
//        Window dialogWindow = getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
//        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
//        dialogWindow.setAttributes(lp);


        //以下是底部样式
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        lp.width = (int) (d.widthPixels);
        window.setAttributes(lp);

    }

    public interface ClickListenerInterface {
        //点击某个 然后由activity处理
        public void doDelete();

        public void doCancel();
    }


    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id==R.id.btn_delete){
                listenerInterface.doDelete();
            }
            if(id==R.id.btn_cancel){
                listenerInterface.doCancel();
            }



        }
    }
}

//    private void deleteData(final String p_id) {
//        View view = LayoutInflater.from(mContext)
//                .inflate(R.layout.commentpopmenu, null);
//        dialog = new Dialog(mContext, R.style.chooseDialog);
//        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//        final Button btn_delete = (Button) view.findViewById(R.id.btn_delete);
//        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
//        btn_cancel.setText("取消");
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
//        btn_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteComment(p_id);
//                dialog.cancel();
//            }
//        });
//        //在show调用之后设置宽度铺满
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.BOTTOM);
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = CommonTools.getScreenWidth(mContext);
//        window.setAttributes(lp);
//    }