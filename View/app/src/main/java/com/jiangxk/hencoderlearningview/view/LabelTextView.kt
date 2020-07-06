package com.jiangxk.hencoderlearningview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.jiangxk.hencoderlearningview.extension.dp2Px
import kotlin.random.Random

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-06-10  22:14
 */

class LabelTextView : AppCompatTextView {

    companion object {
        val TEXT_SIZE = intArrayOf(12, 15, 18, 24, 30)
        val COLORS = intArrayOf(
            Color.parseColor("#FF4081"),
            Color.parseColor("#9C27B0"),
            Color.parseColor("#448AFF"),
            Color.parseColor("#4CAF50"),
            Color.parseColor("#CDDC39"),
            Color.parseColor("#FF5722")
        )
        val CORNER_RADIUS = 8.dp2Px()
        val X_PANDDING = 16.dp2Px().toInt()
        val Y_PANDDING = 8.dp2Px().toInt()

        private val provinces = listOf(
            "北京市",
            "天津市",
            "上海市",
            "重庆市",
            "河北省",
            "山西省",
            "辽宁省",
            "吉林省",
            "黑龙江省",
            "江苏省",
            "浙江省",
            "安徽省",
            "福建省",
            "江西省",
            "山东省",
            "河南省",
            "湖北省",
            "湖南省",
            "广东省",
            "海南省",
            "四川省",
            "贵州省",
            "云南省",
            "陕西省",
            "甘肃省",
            "青海省",
            "台湾省",
            "内蒙古自治区",
            "广西壮族自治区",
            "西藏自治区",
            "宁夏回族自治区",
            "新疆维吾尔自治区",
            "香港特别行政区",
            "澳门特别行政区"
        )
    }

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val random = Random.Default

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        setTextColor(Color.WHITE)
        textSize = TEXT_SIZE[random.nextInt(TEXT_SIZE.size)].toFloat()
        paint.color = COLORS[random.nextInt(COLORS.size)]
        setPadding(X_PANDDING, Y_PANDDING, X_PANDDING, Y_PANDDING)
        text = provinces[random.nextInt(provinces.size)]
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            CORNER_RADIUS,
            CORNER_RADIUS, paint
        )
        super.onDraw(canvas)
    }

}