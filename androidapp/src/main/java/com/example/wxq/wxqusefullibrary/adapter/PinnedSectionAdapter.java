package com.example.wxq.wxqusefullibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wxq.wxqusefullibrary.R;
import com.example.wxq.wxqusefullibrary.model.Function;
import com.example.wxq.wxqutilslibrary.widget.listview.PinnedSectionListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import specialtools.DensityUtils;

/**
 * 分好类的适配器.加载的是分好组的list数据
 *
 * @author wxq
 */
public class PinnedSectionAdapter extends BaseAdapter implements
        PinnedSectionListView.PinnedSectionListAdapter {
    // 时间以及相关信息
    private ArrayList<Function> list;//这个是分好类别后的list,在所属activity进行数据分类
    private Context mContext;
    private static int TYPE_FOOTER = 2;  //加载更多
    private String nowTimeStr;

    public boolean isEnableLoadMore() {
        return enableLoadMore;
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
    }

    private boolean enableLoadMore = true;

    public LoadMoreListener getListener() {
        return listener;
    }

    public void setListener(LoadMoreListener listener) {
        this.listener = listener;
    }

    private LoadMoreListener listener;
    int mPosition;

    public ArrayList<Function> getList() {
        return list;
    }

    public void setList(ArrayList<Function> list) {
        if (list != null) {
            this.list = list;
        } else {
            list = new ArrayList<Function>();
        }
    }

    public PinnedSectionAdapter(ArrayList<Function> list, Context mContext) {
        super();
        this.setList(list);
        this.mContext = mContext;
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "MM月dd日");
        nowTimeStr = dateFormat.format(nowTime);


    }

    final static class ViewHolderTime {
        TextView time;

    }

    final static class ViewHolderContent {
        TextView title, username;
        ImageView image, line;
        RelativeLayout rl_total;

    }


    final static class ViewHolderFoot {


    }

    @Override
    public int getCount() {
        if (list.size() > 0 && enableLoadMore) {
            return list.size() + 1;
        }
        return list.size();
    }

    @Override
    public Function getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        int type = getItemViewType(position);

        ViewHolderFoot viewHolderFoot = null;
        ViewHolderTime viewHolderTime = null;
        ViewHolderContent viewHolderContent = null;


        if (convertView == null) {
            switch (type) {
                case 1:

                    viewHolderContent = new ViewHolderContent();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.function_list_item1, null);
                    viewHolderContent.title = (TextView) convertView.findViewById(R.id.tv_content);
                    convertView.setTag(viewHolderContent);
                    break;
                case 0:

                    viewHolderTime = new ViewHolderTime();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.function_list_item0, null);
                    viewHolderTime.time = (TextView) convertView.findViewById(R.id.tv_title);
                    convertView.setTag(viewHolderTime);
                    break;
                case 2:
                    //加载更多
                    viewHolderFoot = new ViewHolderFoot();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.moredata, null);
                    convertView.setTag(viewHolderFoot);
                    break;

            }


        } else {
            switch (type) {
                case 1:
                    viewHolderContent = (ViewHolderContent) convertView.getTag();
                    break;
                case 0:
                    viewHolderTime = (ViewHolderTime) convertView.getTag();
                    break;
                case 2:
                    viewHolderFoot = (ViewHolderFoot) convertView.getTag();
                    break;
            }

        }
        switch (type) {
            case 1:
                final Function bean = list.get(position);
                viewHolderContent.title.setText(bean.getName());
                break;
            case 0:
                viewHolderTime.time.setHeight(DensityUtils.dp2px(mContext, 60));
                viewHolderTime.time.setText(list.get(position).getName());
                break;
            case 2:
                //不获取数据否则数组越界
                if (listener != null) {
                    listener.loadMoreDate();  //jia
                }
                break;
        }


        return convertView;

    }

    //判断是否是属于标题悬浮的
    @Override
    public boolean isItemViewTypePinned(int viewType) {

       return viewType == 0;//为0 的话就是标题栏
    //    return false;//为0 的话就是标题栏
    }

    //arraylist的数据里面有2种类型,标题和内容
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {

        if (list.size() == position && list.size() > 0) {

            return TYPE_FOOTER;
        }
        mPosition = position;
        return ((Function) getItem(position)).getType();
    }

    public interface LoadMoreListener {

        void loadMoreDate();

        void updateHeard(String text);

    }
}
