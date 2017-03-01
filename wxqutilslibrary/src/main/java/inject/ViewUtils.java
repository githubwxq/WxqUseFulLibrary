package inject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class ViewUtils {
    public static void injectContentView(Activity activity) {
        Class a = activity.getClass();  //获取类别
        if (a.isAnnotationPresent(ContentView.class)) {
            ContentView contentView = (ContentView) a.getAnnotation(ContentView.class);
            int layoutId = contentView.value();//定义的属性值

            try {
                Method method = a.getMethod("setContentView", int.class);//参数类型
                method.setAccessible(true);
                method.invoke(activity, layoutId);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }


    }

    public static void injectViews(Activity activity) {
        Class a = activity.getClass();
        // 得到activity所有字段
        Field[] fields = a.getDeclaredFields();
        // 得到被ViewInject注解的字段
        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewInject.class)) {
                // 得到字段的ViewInject注解
                ViewInject viewInject = field.getAnnotation(ViewInject.class);
                // 得到注解的值
                int viewId = viewInject.value();
                // 使用反射调用findViewById，并为字段设置值
                try {
                    Method method = a.getMethod("findViewById", int.class);
                    method.setAccessible(true);
                    Object resView = method.invoke(activity, viewId);
                    field.setAccessible(true);
                    field.set(activity, resView);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    // 监听器注解 setonclicklistener注解

    public static void injectEvent(Activity activity) {
        Class a = activity.getClass();

        Method[] methods = a.getMethods();// 获取actvity中的所有方法

//    遍历得到onclick方法注解的方法

        for (Method method : methods) {
            if (method.isAnnotationPresent(OnClick.class)) {
                // 得到该注解
                OnClick onClick = method.getAnnotation(OnClick.class);
                //得到注解值

                int[] viewIds = onClick.value();  //注解刚声明的值
                // 得到注解的注解
                EventBase eventBase = onClick.annotationType().getAnnotation(EventBase.class);
                String listenerSetter = eventBase.listenerSetter();
                Class listenerType = eventBase.listenerType();
                String methodName = eventBase.methodName();
                //使用动态代理
                DynamicHandler handler = new DynamicHandler(activity);
//            listenerType }, handler);
//           handler.addMethod(methodName, method);               listenerType 某个接口
                Object listener = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class<?>[]{listenerType}, handler);
                handler.addMethod(methodName, method);
                for (int viewId : viewIds) {
                    try {
                        Method findViewByIdMethod = a.getMethod("findViewById", int.class);
                        findViewByIdMethod.setAccessible(true);
                        View view = (View) findViewByIdMethod.invoke(activity, viewId); //早到了对应的控件添加监听时间
                        Method setEventListenerMethod = view.getClass().getMethod(listenerSetter, listenerType);//设置setonclicklistener
                        setEventListenerMethod.setAccessible(true);
                        setEventListenerMethod.invoke(view, listener);    //di第一个参数表示是谁的方法 第二个表示是各种参数
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }


                }

            }


        }


    }
}






 // 注解反射使用
//
//@ContentView(R.layout.activity_main)
//public class MainActivity extends Activity {
//    @XInject(id=R.id.tv_content)
//    private TextView tv_tonctent;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ViewUtils.injectContentView(this);
//        //    setContentView(R.layout.activity_main);
//        //案例2;
//        Inject.register(this);
//        tv_tonctent.setText("JAVA反射");
////        tv_tonctent.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Toast.makeText(MainActivity.this,"lallaal",Toast.LENGTH_LONG).show();
////            }
////        });
//        ViewUtils.injectEvent(this);
//        //案例1;
////        initArray();
////        initList();
////        initMap();
//        initObject();
//
//    }
//
//    @OnClick({R.id.tv_content})
//    public void clickBtnInvoked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_content:
//                Toast.makeText(this, "Button1 OnClick", Toast.LENGTH_SHORT).show();
//                break;
//
//        }
//    }