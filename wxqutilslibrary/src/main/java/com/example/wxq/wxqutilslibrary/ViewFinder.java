package com.example.wxq.wxqutilslibrary;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

public final class ViewFinder {
      
    static LayoutInflater mInflater;
   
    /**  
     * 每项的View的sub view Map  
     */    
    private static SparseArray<View> mViewMap = new SparseArray<View>();
   
    /**  
     * Content View  
     */    
    static View mContentView;    
   
    /**  
     * 初始化ViewFinder, 实际上是获取到该页面的ContentView.  
     *   
     * @param context  
     * @param layoutId  
     */    
    public static void initContentView(Context context, int layoutId) {
        mInflater = LayoutInflater.from(context);    
        mContentView = mInflater.inflate(layoutId, null, false);    
        if (mInflater == null || mContentView == null) {    
            throw new RuntimeException(    
                    "ViewFinder init failed, mInflater == null || mContentView == null.");    
        }    
    }     
    public static View getContentView() {    
        return mContentView;    
    }     
      
    public static <T extends View> T findViewById(int viewId) {    
        // 先从view map中查找,如果有的缓存的话直接使用,否则再从mContentView中找    
        View tagetView = mViewMap.get(viewId);    
        if (tagetView == null) {    
            tagetView = mContentView.findViewById(viewId);    
            mViewMap.put(viewId, tagetView);    
        }    
        return tagetView == null ? null : (T) mContentView.findViewById(viewId);    
    }    
}  