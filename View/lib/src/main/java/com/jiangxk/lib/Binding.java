package com.jiangxk.lib;

import android.app.Activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author jiangxk
 * @description com.jiangxk.annotationprocessing
 * @time 2020/7/26  6:20 PM
 */
public class Binding {
    public static void bind(Activity activity) {
        String className = activity.getClass().getCanonicalName() + "Binding";

        try {
            Class bindingClass = Class.forName(className);
            Constructor constructor = bindingClass.getDeclaredConstructor(activity.getClass());
            constructor.newInstance(activity);

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
