package viewgroupadapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/12/18.
 */
public class ViewGroupUtils { // 帮助类

    public static void addViews(final ViewGroup viewGroup, IViewGroupAdapter adapter) {
        addViews(viewGroup, adapter, true, null, null);
    }

    public static void addViews(final ViewGroup viewGroup, IViewGroupAdapter adapter
            , final OnItemClickListener onItemClickListener) {
        addViews(viewGroup, adapter, true, onItemClickListener, null);
    }

    public static  void addViews(final ViewGroup viewGroup,IViewGroupAdapter adapter,boolean removeViews,final OnItemClickListener onItemClickListener,final OnItemLongClickListener onItemLongClickListener){
       if(viewGroup==null||adapter==null){
           return;
       }

        if(removeViews && viewGroup.getChildCount()>0){
            viewGroup.removeAllViews();
        }

        int count=adapter.getCount();

        for (int i=0;i<count;i++){
            View itemView=adapter.getView(viewGroup,i);// 适配器
            viewGroup.addView(itemView);
         if(null!=onItemClickListener&& !itemView.isClickable()){
             final int finali = i;
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     onItemClickListener.onItemClick(viewGroup,view,finali ); //由上层处理

                 }
             });

             //添加长按事件itemView之前没有长按事件才会去设置
             if (null != onItemLongClickListener && !itemView.isLongClickable()) {
                 final int finalI = i;
                 itemView.setOnLongClickListener(new View.OnLongClickListener() {
                     @Override
                     public boolean onLongClick(View view) {
                         return onItemLongClickListener.onItemLongClick(viewGroup, view, finalI);
                     }
                 });
             }
         }






        }










    }





}
