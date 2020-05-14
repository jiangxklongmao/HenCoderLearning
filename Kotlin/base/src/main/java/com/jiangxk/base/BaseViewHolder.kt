package com.jiangxk.base

import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @description com.jiangxk.base
 * @author jiangxk
 * @time 2020-05-13  22:28
 */
open class BaseViewHolder : RecyclerView.ViewHolder {
    constructor(itemVIew: View) : super(itemVIew)

    private val viewMap = hashMapOf<Int, View?>()

    fun <T : View> getView(@IdRes id: Int): T {
        var view = viewMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewMap[id] = view
        }
        return view as T
    }

    fun setText(@IdRes id: Int, text: String?) {
        getView<TextView>(id).text = text
    }

}