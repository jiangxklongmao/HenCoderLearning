package com.jiangxk.lib_annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiangxk
 * @description com.jiangxk.lib_reflection
 * @time 2020/7/26  6:09 PM
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface BindView {

    int value();

    String name() default "";
}