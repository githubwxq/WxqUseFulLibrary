package com.example.wxq.wxqutilslibrary.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * BottomView
 * dialog 和 popupwindow不同于布局他们是窗口层 而布局这只是画布层
 *
 * @author TanDong、//
 * 需要dialog 给dialog套一个类 这个类处理dialog多余的方法提供对外参数 抽取dialog中核心代码最后显示
 */
public class BottomView {
	private View convertView;
	private Context context;
	private int theme;
	private Dialog bv;
	private int animationStyle;

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean top) {
		isTop = top;
	}

	private boolean isTop = false;

	public BottomView(Context c, int theme, View convertView) {
		this.theme = theme;
		this.context = c;
		this.convertView = convertView;
	}

	public BottomView(Context c, int theme, int resource) {
		this.theme = theme;
		this.context = c;
		this.convertView = View.inflate(c, resource, null);
	}

	public void showBottomView(boolean CanceledOnTouchOutside) {
		if (theme == 0) {
			bv = new Dialog(context);//show 调用时菜创建
		} else {
			bv = new Dialog(context, theme);
		}
		bv.setCanceledOnTouchOutside(CanceledOnTouchOutside);
		bv.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		bv.setContentView(convertView);
		Window wm = bv.getWindow();
		WindowManager m = wm.getWindowManager();
		Display d = m.getDefaultDisplay();
		WindowManager.LayoutParams p = wm.getAttributes();
		p.width = (int) (d.getWidth() * 1);
		if (isTop) {
			p.gravity = Gravity.TOP;
		} else {
			p.gravity = Gravity.BOTTOM;
		}
		if (animationStyle == 0) {
		} else {
			wm.setWindowAnimations(animationStyle);
		}
		wm.setAttributes(p);
		bv.show();
	}

	public void setTopIfNecessary() {
		isTop = true;
	}

	public void setAnimation(int animationStyle) {
		this.animationStyle = animationStyle;
	}

	public View getView() {
		return convertView;
	};

	public void dismissBottomView() {
		if (bv != null) {
			bv.dismiss();
		}
	}
}



//如下使用方法
//bottomView=new BottomView(this, R.style.BottomViewTheme_Defalut, R.layout.activity_dialog);
//
//		bottomView.setTop(false);
//		bottomView.showBottomView(true);
//		bottomView.dismissBottomView();