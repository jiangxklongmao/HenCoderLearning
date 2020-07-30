package com.jiangxk.lib_reflection;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * @author jiangxk
 * @description com.jiangxk.lib_reflection
 * @time 2020/7/26  6:09 PM
 */
public class Binding {
    public static void bind(Activity activity) {
        for (Field field : activity.getClass().getDeclaredFields()) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                try {
                    field.setAccessible(true);
                    field.set(activity, activity.findViewById(bindView.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
