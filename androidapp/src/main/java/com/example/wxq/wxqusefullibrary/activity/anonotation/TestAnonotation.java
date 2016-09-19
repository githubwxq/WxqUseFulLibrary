package com.example.wxq.wxqusefullibrary.activity.anonotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wxq on 2016/7/26.
 *类型 参数名() default 默认值;
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TestAnonotation {
    String value();
    String[] value2() default "value2";
}
