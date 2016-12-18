package viewgroupadapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/12/18.
 */
public interface IViewGroupAdapter {


    View getView(ViewGroup parent ,int pos);

    int getCount();
}
