package com.jiangxk.hencoderlearningview.view.drag

import android.content.ClipData
import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.view.View.OnLongClickListener
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.view.ViewCompat
import kotlinx.android.synthetic.main.activity_drag_to_collect.view.*

/**
 * @description com.jiangxk.hencoderlearningview.View.drag
 * @author jiangxk
 * @time 2020/7/2  6:09 PM
 */
class DragToCollectLayout : RelativeLayout {


    private val dragStarter = OnLongClickListener { v ->
        val imageData = ClipData.newPlainText("name", v.contentDescription)
        v.performHapticFeedback(
            HapticFeedbackConstants.LONG_PRESS,
            HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
        )

        val dragShadowBuilder = DragShadowBuilder(v)
        ViewCompat.startDragAndDrop(v, imageData, dragShadowBuilder, null, 0)
    }

    private var dragListener: OnDragListener = CollectListener()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        avatarView.setOnLongClickListener(dragStarter)
        logoView.setOnLongClickListener(dragStarter)
        collectorLayout.setOnDragListener(dragListener)
    }


    private inner class CollectListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {

                }

                DragEvent.ACTION_DROP -> {
                    if (collectorLayout === v) {
                        println("onDrag")
                        val text = event.clipData.getItemAt(0).text
                        v.post {
                            Toast.makeText(
                                context,
                                text,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
            return true
        }
    }

}