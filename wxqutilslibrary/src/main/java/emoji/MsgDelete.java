package emoji;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;

import com.example.wxq.wxqutilslibrary.R;


/**emoji
 * Created by Administrator on 2016/8/5 0005.
 */
public class MsgDelete {
    private Context mContext;
    private String[] stringArray;
    private static MsgDelete msgDelete;
    private MsgDelete(Context context){
        this.mContext=context;
        stringArray = mContext.getResources().getStringArray(R.array.default_smiley_texts);
    }
    public static MsgDelete getInstance(Context context){
        if(msgDelete==null){
            msgDelete=new MsgDelete(context);
        }
        return  msgDelete;
    }
    public void delete(EditText et_msg){
        boolean flag=false;
        String s = et_msg.getText().toString();
        if(et_msg.getSelectionStart()!=s.length()) {
            String temp = s.substring(0, et_msg.getSelectionStart());
            if (temp.endsWith("]")) {
                if (temp.contains("[")) {
                    int i = temp.lastIndexOf("[");
                    String left = temp.substring(0, i);
                    String rigth = s.substring(temp.length(), s.length());
                    String delete = temp.substring(i, temp.length());
                    for (String aStringArray : stringArray) {
                        if (delete.equals(aStringArray)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        et_msg.setText(left + rigth);
                        et_msg.setSelection(i);

                    } else {
                        et_msg.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    }
                } else {
                    et_msg.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                }
            } else {
                et_msg.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            }
        }else{
            if (s.length() > 0) {
                String last = s.substring(s.length() - 1);
                if (last.equals("]")) {
                    if(s.contains("[")){
                        int i = s.lastIndexOf("[");
                        String a=s.substring(i, s.length());
                        for (String aStringArray : stringArray) {
                            if (a.equals(aStringArray)) {
                                flag = true;
                                break;
                            }
                        }
                        if(flag){
                            String result = s.substring(0, i);
                            et_msg.setText(result);
                            et_msg.setSelection(i);
                        }else{
                            et_msg.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                        }
                    }else{
                        et_msg.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    }

                } else {
                    et_msg.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                }
            }

        }
    }
}
