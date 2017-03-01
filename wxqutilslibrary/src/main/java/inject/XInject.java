package inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by anqiansong on 2016/5/30.
 * 主题:[案例2]注解  、、运行时注解
 * TYPE(类型), FIELD(属性), METHOD(方法), PARAMETER(参数),
 * CONSTRUCTOR(构造函数),LOCAL_VARIABLE(局部变量), ANNOTATION_TYPE,PACKAGE(包),其中的TYPE(类型)是指可以用在Class,Interface,Enum和Annotation类型上.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XInject {
     int id() default -1;   //注解参数
}
