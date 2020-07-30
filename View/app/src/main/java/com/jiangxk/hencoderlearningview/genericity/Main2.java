package com.jiangxk.hencoderlearningview.genericity;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangxk
 * @description com.jiangxk.hencoderlearningview.genericity
 * @time 2020/7/27  5:52 PM
 */
class Main2 {

    public static void main(String[] args) {


//        ArrayList<? extends Fruit> fruits = new ArrayList<Apple>();
////        fruits.add(new Apple());
//        Apple apple = (Apple) fruits.get(0);


//        Fruit[] fruits1 = new Apple[10];
//        fruits1[0] = new Banana();

        ArrayList<Fruit> fruitList = (ArrayList) new ArrayList<Apple>();
        fruitList.add(new Banana());
        System.out.println("Fruit List 添加元素完成");


        List<? super Apple> apples = new ArrayList<Fruit>();
        apples.add(new Apple());
        Apple apple = (Apple) apples.get(0);


        ArrayList<? extends View> viewList = new ArrayList<TextView>();


        ArrayList<? super AppCompatTextView> textViewList = new ArrayList<TextView>();

    }

}
