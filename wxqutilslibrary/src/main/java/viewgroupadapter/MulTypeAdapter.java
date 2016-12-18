package viewgroupadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */
public abstract  class MulTypeAdapter<T extends IMulTypeHelper> extends BaseAdapter<T> {

    public MulTypeAdapter(Context context, List<T> datas) { //布局由对象决定了
        super(context, datas);
    }

    @Override
    public View getView(ViewGroup parent, int pos, T data) {
        View itemView = mInflater.inflate(data.getItemLayoutId(), parent, false);
        onBindView(parent, itemView, data, pos);
        return itemView;
    }

    /**
     * 暴漏这个 让外部bind数据
     *
     * @param parent
     * @param itemView
     * @param data
     * @param pos
     */
    public abstract void onBindView(ViewGroup parent, View itemView, T data, int pos);


}
