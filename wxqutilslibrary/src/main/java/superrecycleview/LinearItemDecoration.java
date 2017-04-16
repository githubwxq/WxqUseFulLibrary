package superrecycleview;

import android.content.Context;
import android.support.annotation.ColorInt;

/**
 * Created by Administrator on 2017/4/16.
 */
public class LinearItemDecoration extends  BaseItemDecoration{
    public LinearItemDecoration(Context context, int lineWidthDp, @ColorInt int colorRGB) {
        super(context, lineWidthDp, colorRGB);
    }

    @Override
    public boolean[] getItemSidesIsHaveOffsets(int itemPosition) {
        //顺时针顺序:left, top, right, bottom
        boolean[] isOffset = {false, false, false, true};//默认只有bottom显示分割线
        return isOffset;
    }
}
