package dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/20.
 */
public class DialogViewHolder {
    private  SparseArray<View> mViews;
    private View mDialogView;

    private  DialogViewHolder(Context context,int layoutID){
        this.mViews=new SparseArray<>();
        mDialogView=View.inflate(context,layoutID,null);


    }

    public View getConcertView(){

        return  mDialogView;
    }

    public static DialogViewHolder get(Context context,int layoutId){

        return new DialogViewHolder(context,layoutId);

    }
    /**
     * Set the string for TextView
     *
     * @param viewId
     * @param text
     * @return
     */
    public DialogViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * set view visible
     *
     * @param viewId
     * @return
     */
    public DialogViewHolder setViewInViSible(int viewId) {
        TextView view = getView(viewId);
        view.setVisibility(View.INVISIBLE);
        return this;
    }

    /**
     * set view visible
     *
     * @param viewId
     * @return
     */
    public DialogViewHolder setViewViSible(int viewId) {
        TextView view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * set view gone
     *
     * @param viewId
     * @return
     */
    public DialogViewHolder setViewGone(int viewId) {
        TextView view = getView(viewId);
        view.setVisibility(View.GONE);
        return this;
    }

    /**
     * 设置点击
     */
    public DialogViewHolder setOnClick(int viewId, View.OnClickListener onClick) {
        View view = getView(viewId);
        view.setOnClickListener(onClick);
        return this;
    }



    //getview 根据id获取所有的view对象
    public <T extends  View> T getView(int viewId){
        
        View view=mViews.get(viewId);
        if (view == null) {
            view = mDialogView.findViewById(viewId);  //获取存放到数据中区
            mViews.put(viewId, view);
        }
        
        return (T)view;
    }
}
