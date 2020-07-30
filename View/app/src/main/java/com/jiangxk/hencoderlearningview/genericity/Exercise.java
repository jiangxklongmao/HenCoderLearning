package com.jiangxk.hencoderlearningview.genericity;

import java.io.Serializable;
import java.util.List;

/**
 * @author jiangxk
 * @description com.jiangxk.hencoderlearningview.genericity
 * @time 2020/7/28  2:19 PM
 */
class Exercise<T extends Runnable & Serializable> {

    void someMethod(T param) {

    }


    void merge(T item, List<T> list) {
        list.add(item);
    }

}
