# WxqUseFulLibrary
王晓清整合的app案例工具大集合
如何写简介
如何添加图片
如何添加链接
如何点击跳转
王晓清整合的app案例工具大集合
如何写简介
如何添加图片
如何添加链接
如何点击跳转王晓清整合的app案例工具大集合
如何写简介如何添加图片如何添加链接
如何点击跳转

      Features
      	--
      * 支持自定义滚轮样式
      * 支持common和holo两种皮肤
      * 支持文本和图文混排两中数据模版
      * 支持循环显示数据
      * 支持选中项添加附加文本
      * 支持设置滚轮刻度

      ScreenShot
      --
      ![image](https://github.com/githubwxq/WxqUseFulLibrary/blob/master/screenpic/testone.png)
      ![image](https://github.com/githubwxq/WxqUseFulLibrary/blob/master/screenpic/testtwo.png)
      ![image](https://github.com/venshine/WheelView/blob/master/screenshot/screenshot2.png)
      ##### Methods:
    | method 方法          | description 描述 |
	|:---				   |:---|
	| void **setWheelAdapter**(BaseWheelAdapter<T> adapter)  	     | 设置滚轮数据源适配器（required） |
	| void **setWheelData**(List<T> list)  	     | 设置滚轮数据（required） |
	| void **setLoop**(boolean loop)  	     | 设置滚轮是否循环滚动 |
	| void **setWheelSize**(int wheelSize) 	     | 设置滚轮个数 |
	| void **setSkin**(Skin skin) 	     | 设置皮肤风格 |
	| Skin **getSkin**()  	     | 获得皮肤风格 |
	| void **setStyle**(WheelViewStyle style)  	     | 设置滚轮样式 |
	| WheelViewStyle **getStyle**()  	     | 获得滚轮样式 |
	| void **setWheelClickable**(boolean clickable)  	     | 设置滚轮选中项是否可点击 |
	| void **setSelection**(final int selection) 	     | 设置滚轮位置 |
	| int **getSelection**() 	     | 获取滚轮位置 |
	| void **join**(WheelView wheelView)  	     | 连接副WheelView（联动设置） |
	| void **joinDatas**(HashMap<String, List<T>> map)	     | 副WheelView数据（联动设置） |
	| int **getCurrentPosition**()  	     | 获取当前滚轮位置 |
	| T **getSelectionItem**()  	     | 获取当前滚轮位置的数据 |
	| void **setExtraText**(String text, int textColor, int textSize, int margin)     	     | 设置选中行附加文本 |
	| int **getWheelCount**() 	     | 获得滚轮数据总数 |
	| void **setOnWheelItemSelectedListener**(OnWheelItemSelectedListener<T> onWheelItemSelectedListener) | 设置滚轮滑动停止时事件，监听滚轮选中项 |
	| void **setOnWheelItemClickListener**(OnWheelItemClickListener<T> onWheelItemClickListener) | 设置滚轮选中项点击事件 |
