
package inject;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * Created by anqiansong on 2016/5/30.
 * 主题:注解实体类
 */
public class Inject {
    public static void register(Activity activity){
        Class clazz=activity.getClass();
        //获取全部变量
        Field[] fields=clazz.getDeclaredFields();
        for(Field f:fields){
            //判断字段是否在activity中使用注解
            if(f.isAnnotationPresent(XInject.class)){
                //获取注解class
                XInject inject=f.getAnnotation(XInject.class);
                int id=inject.id();//inject获取值
                if(id!=-1){
                    f.setAccessible(true);
                    try {
                        f.set(activity,activity.findViewById(id)); //给属性赋值
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
