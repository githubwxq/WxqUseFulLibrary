package emoji;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.wxq.wxqutilslibrary.R;

import java.util.ArrayList;



/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月29日
 * @description 表情面板的适配器
 */
public class EmojiGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private int itemWidth=0;
    private ArrayList<String> emojiName=null;
    private ArrayMap<String, Integer> map= new ArrayMap<>();

    public EmojiGridViewAdapter(Context mContext, int itemWidth,
                                ArrayList<String> emojiName, ArrayMap<String, Integer> map) {
        this.mContext = mContext;
        this.itemWidth = itemWidth;
        this.emojiName = emojiName;
        this.map = map;
    }

    @Override
    public int getCount() {
        return emojiName.size()+1;
    }

    @Override
    public String getItem(int position) {
        return emojiName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img=new ImageView(mContext);
        AbsListView.LayoutParams params=new AbsListView.LayoutParams(itemWidth, itemWidth);
        img.setPadding(itemWidth/8, itemWidth/8, itemWidth/8, itemWidth/8);
        img.setLayoutParams(params);
        if(position==getCount()-1){
            img.setImageResource(R.mipmap.face_delete);
        }else{
            img.setImageResource(map.get(emojiName.get(position)));
        }
        return img;
    }

}
