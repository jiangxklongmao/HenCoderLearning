package com.jiangxk.annotationprocessing;

import android.app.Activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author jiangxk
 * @description com.jiangxk.annotationprocessing
 * @time 2020/7/26  6:20 PM
 */
class InnerBinding {
    public static void bind(Activity activity) {
//        activity.textView = activity.findViewById(R.id.textView);

//        for (Field field : activity.getClass().getDeclaredFields()) {
//            BindView bindView = field.getAnnotation(BindView.class);
//            if (bindView != null) {
//                try {
//                    field.set(activity, activity.findViewById(bindView.value()));
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//

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
