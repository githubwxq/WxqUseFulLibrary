package viewgroupadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
public abstract class SingleAdapter<T> extends BaseAdapter<T> {  //单个SingleAdapter 以匿名内部类对象座位参数 子类方法父类决定
    public int mItemLayoutId;

    public SingleAdapter(Context context,List<T> datas ,int itemLayoutId){
        super(context ,datas);
        mItemLayoutId=itemLayoutId;
    }

    @Override
    public View getView(ViewGroup parent, int pos, T data) {  //最后被上一层调用
        View itemView=mInflater.inflate(mItemLayoutId,parent,false);
       //绑定view
        onBindView(parent,itemView,data,pos);  // 被回调到最外层
        return  itemView;
    }


    public abstract void onBindView(ViewGroup parent ,View itemView,T data,int pos);

}
