//package com.example.wxq.wxqusefullibrary;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.example.wxq.wxqusefullibrary.model.Book;
//import com.example.wxq.wxqusefullibrary.widget.CustomView1;
//import com.example.wxq.wxqusefullibrary.widget.JustForTestWidget.reactview;
//import com.example.wxq.wxqutilslibrary.activity.BaseActivity;
//import com.hwangjr.rxbus.annotation.Subscribe;
//import com.hwangjr.rxbus.annotation.Tag;
//import com.hwangjr.rxbus.thread.EventThread;
//
//// rxbus 测试成功
//
//public class MyRxActivity extends BaseActivity {
//   Button rxbus_change;
//   LinearLayout addwidget;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_rx);
//        RxBus.get().register(this);
//        //也可以自己使用
//        addwidget= (LinearLayout) findViewById(R.id.add_widget);
//      //  addwidget.addView(new CustomView1(this));
//        addwidget.addView(new CustomView1(this));
//        addwidget.addView(new reactview(this));
//
//
//        rxbus_change= (Button) findViewById(R.id.rxbus_change);
//
//        rxbus_change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // rxbusc测试
//
//                RxBus.get().post("我是从第三个界面发过来的值");
//
//                Book book=new Book("rxbook ","1000");
//
//               RxBus.get().post(book);
//
//              //  RxBus.get().post("mybook",book);//打个标签区分不同的对象
//            }
//        });
//
//    }
//
//    @Subscribe
//    public void localaccept(String food) {
//        Toast.makeText(this,food+"我在当前类",Toast.LENGTH_SHORT).show();
//    }
//    @Subscribe
//    public void getBook(Book book) {
//        Toast.makeText(this,book.getName()+book.getName()
//                +"我在当前类",Toast.LENGTH_SHORT).show();
//    }
//
//
//    @Subscribe(
//            thread = EventThread.MAIN_THREAD,
//            tags = {
//                    @Tag("mybook")
//            }
//    )
//    public void eatMore(Book book) {
//        // purpose
//        Toast.makeText(this,"mybook"+ book.getName(),Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //销毁
//        RxBus.get().unregister(this);
//    }
//
//    @Override
//    public void widgetClick(View v) {
//
//    }
//
//}
