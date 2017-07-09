package viewpage.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class AbstractViewPagerAdapter<T> extends PagerAdapter {
    protected List<T> mData;  
    private SparseArray<View> mViews;
  
    public AbstractViewPagerAdapter(List<T> data) {
        mData = data;  
        mViews = new SparseArray<View>(data.size());  
    }  
  
    @Override  
    public int getCount() {  
        return mData.size();  
    }  
  
    @Override  
    public boolean isViewFromObject(View view, Object object) {  
        return view == object;  
    }  
  
    @Override  
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);  
        if (view == null) {  
            view = newView(position);  
            mViews.put(position, view);  
        }  
        container.addView(view);  
        return view;  
    }  
  
    public abstract View newView(int position);  
  
    @Override  
    public void destroyItem(ViewGroup container, int position, Object object) {  
        container.removeView(mViews.get(position));  
    }  
  
    public T getItem(int position) {  
        return mData.get(position);  
    }  
}


//、、使用方法

//class OpenResultAdapter extends AbstractViewPagerAdapter<OpenResult> {
//
//    public OpenResultAdapter(List<OpenResult> data) {
//        super(data);
//    }
//
//    @Override
//    public View newView(int position) {
//        View view = View.inflate(mContext, R.layout.view_remote_capture, null);
//        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
//        UIUtil.setLayoutParamsHeight(imageView, R.dimen.padding_common, 4, 3);
//        imageView.setAdjustViewBounds(true);
//        mImageLoader.displayImage(UrlUtil.imageUrl(getItem(position).getImgUrl()), imageView);
//        return view;
//    }
//}