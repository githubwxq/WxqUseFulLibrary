package inject;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class DynamicHandler implements InvocationHandler {
    private final HashMap<String ,Method> methodMap=new HashMap<>();
    // 因为传进来的为activity，使用弱引用主要是为了防止内存泄漏
    private WeakReference<Object> handlerRef;
    public DynamicHandler(Object object) {
        this.handlerRef = new WeakReference<Object>(object);
    }

    public void addMethod(String name, Method method) {
        methodMap.put(name, method);
    }
//
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object handler = handlerRef.get();
        if (handler != null) {
            // method对应的就是回调方法OnClick，得到方法名
            String methodName = method.getName();
            Log.i("wxq",methodName);
            // 得到activtiy里面的clickBtnInvoked方法
            method = methodMap.get(methodName);  // 获取接口中的
            if (method != null) {
                // 回调clickBtnInvoked方法
                return method.invoke(handler, objects);
            }
        }
        return null;

    }
}
