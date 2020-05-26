package com.jiangxk.hencoderlearningview.View

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.core.graphics.minus
import com.jiangxk.hencoderlearningview.R
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-05-25  16:19
 */
class DashBoardView : View {

    companion object {
        const val DASH_BOARD_START_ANGLE = 150f
        const val DASH_BOARD_END_ANGLE = 240f
        val DASH_BOARD_BACKGROUND_RADIUS = 120f.dp2Px()
        val DASH_BOARD_BACKGROUND_BOTTOM_BREAK_DISTANCE = 100f.dp2Px()
        val DASH_BOARD_RADIUS = 100f.dp2Px()
        const val DASH_BOARD_SCALE_SIZE = 12
        val DASH_BOARD_SCALE_WIDTH = 1f.dp2Px()
        val DASH_BOARD_SCALE_HEIGHT = 10f.dp2Px()
        val DASH_BOARD_SMALL_SCALE_HEIGHT = 5f.dp2Px()
    }

    /**
     * 仪表盘背景
     */
    private lateinit var dashBoardBackgroundPaint: Paint

    /**
     * 仪表盘刻度
     */
    private lateinit var dashBoardScalePaint: Paint
    private lateinit var dashBoardScalePath: Path
    /**
     * 刻度矩形
     */
    private lateinit var scalePath: Path
    /**
     * 小刻度矩形
     */
    private lateinit var smallScalePath: Path

    private lateinit var scalePathDashPathEffect: PathDashPathEffect
    private lateinit var smallScalePathDashPathEffect: PathDashPathEffect

    /**
     * 刻度圆弧 相对于大背景的偏移值
     */
    private var dashBoardOffset = 0f

    /**
     * 路径测量
     */
    private lateinit var pathMeasure: PathMeasure

    /**
     * 大刻度间隔
     */
    private var scaleInterval = 0f

    /**
     * 速度画笔  和 路径
     */
    private lateinit var velocityValuePaint: Paint
    private lateinit var velocityValuePath: Path

    /**
     * 指针
     */
    private lateinit var pointerPaint: Paint

    /**
     * 速度文本
     */
    private lateinit var velocityPaint: Paint

    //渐变背景
    private lateinit var shaderPaint: Paint
    private lateinit var shaderPath: Path

    /**
     * 当前速度值
     */
    var velocityValue = 0f
        set(value) {
            field = value
            invalidate()
        }


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        initView()
    }


    private fun initView() {
        dashBoardBackgroundPaint = Paint().apply {
            color = resources.getColor(R.color.colorAccent)
            isAntiAlias = true
            style = Paint.Style.FILL_AND_STROKE
        }


        dashBoardScalePaint = Paint().apply {
            color = Color.WHITE
            strokeWidth = 10f.dp2Px()
            isAntiAlias = true
            style = Paint.Style.STROKE
        }
        dashBoardScalePath = Path()

        scalePath = Path()
            .apply {
                addRect(
                    0f,
                    0f,
                    DASH_BOARD_SCALE_WIDTH,
                    DASH_BOARD_SCALE_HEIGHT,
                    Path.Direction.CCW
                )
            }
        smallScalePath = Path().apply {
            addRect(
                0f,
                0f,
                DASH_BOARD_SCALE_WIDTH,
                DASH_BOARD_SMALL_SCALE_HEIGHT,
                Path.Direction.CCW
            )
        }

        pathMeasure = PathMeasure()

        velocityValuePaint = Paint().apply {
            color = Color.WHITE
            strokeWidth = 0.5f.dp2Px()
            isAntiAlias = true
            style = Paint.Style.FILL_AND_STROKE
            textSize = 11f.dp2Px()
        }

        velocityValuePath = Path()

        pointerPaint = Paint().apply {
            color = resources.getColor(R.color.colorAccent)
            isAntiAlias = true
            strokeWidth = 2f.dp2Px()
            style = Paint.Style.FILL_AND_STROKE
        }

        shaderPaint = Paint().apply {
            isAntiAlias = true
            strokeWidth = 1f.dp2Px()
            style = Paint.Style.FILL_AND_STROKE
            color = resources.getColor(R.color.colorPrimary)
        }
        shaderPath = Path()


        velocityPaint = Paint().apply {
            color = Color.WHITE
            strokeWidth = 0.5f.dp2Px()
            isAntiAlias = true
            style = Paint.Style.FILL_AND_STROKE
            textSize = 15f.dp2Px()
            textAlign = Paint.Align.CENTER
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)


        dashBoardOffset =
            sqrt(DASH_BOARD_BACKGROUND_RADIUS * DASH_BOARD_BACKGROUND_RADIUS - DASH_BOARD_BACKGROUND_BOTTOM_BREAK_DISTANCE / 2 * DASH_BOARD_BACKGROUND_BOTTOM_BREAK_DISTANCE / 2) - sqrt(
                DASH_BOARD_RADIUS * DASH_BOARD_RADIUS - DASH_BOARD_BACKGROUND_BOTTOM_BREAK_DISTANCE / 2 * DASH_BOARD_BACKGROUND_BOTTOM_BREAK_DISTANCE / 2
            )

        dashBoardScalePath.addArc(
            width / 2 - DASH_BOARD_RADIUS + 10f.dp2Px(),
            height / 2 - DASH_BOARD_RADIUS + 10f.dp2Px() + dashBoardOffset,
            width / 2 + DASH_BOARD_RADIUS - 10f.dp2Px(),
            height / 2 + DASH_BOARD_RADIUS - 10f.dp2Px() + dashBoardOffset,
            DASH_BOARD_START_ANGLE,
            DASH_BOARD_END_ANGLE
        )

        pathMeasure.setPath(dashBoardScalePath, false)

        val interval = (pathMeasure.length - DASH_BOARD_SCALE_WIDTH) / (DASH_BOARD_SCALE_SIZE - 1)


        scalePathDashPathEffect =
            PathDashPathEffect(scalePath, interval, 0f, PathDashPathEffect.Style.ROTATE)

        smallScalePathDashPathEffect =
            PathDashPathEffect(
                smallScalePath,
                interval,
                interval / 2,
                PathDashPathEffect.Style.ROTATE
            )

        velocityValuePath.addArc(
            width / 2 - DASH_BOARD_RADIUS + 10f.dp2Px() + 30f.dp2Px(),
            height / 2 - DASH_BOARD_RADIUS + 10f.dp2Px() + 30f.dp2Px() + dashBoardOffset,
            width / 2 + DASH_BOARD_RADIUS - 10f.dp2Px() - 30f.dp2Px(),
            height / 2 + DASH_BOARD_RADIUS - 10f.dp2Px() - 30f.dp2Px() + dashBoardOffset,
            DASH_BOARD_START_ANGLE,
            DASH_BOARD_END_ANGLE
        )

        pathMeasure.setPath(velocityValuePath, false)

        scaleInterval = (pathMeasure.length - DASH_BOARD_SCALE_WIDTH) / (DASH_BOARD_SCALE_SIZE - 1)

        shaderPaint.shader = RadialGradient(
            width / 2f,
            height / 2f + dashBoardOffset,
            DASH_BOARD_RADIUS,
            resources.getColor(R.color.transparent_70_colorPrimary),
            resources.getColor(R.color.transparent),
            Shader.TileMode.CLAMP
        )

        shaderPath.addCircle(
            width / 2f,
            height / 2f + dashBoardOffset,
            DASH_BOARD_RADIUS, Path.Direction.CW
        )
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //背景
        canvas.drawColor(Color.BLACK)

        //叠加背景圆
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), dashBoardBackgroundPaint)

        canvas.drawCircle(
            width / 2f,
            height / 2f,
            DASH_BOARD_BACKGROUND_RADIUS, dashBoardBackgroundPaint
        )

        dashBoardBackgroundPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

        canvas.drawCircle(
            width / 2f,
            height / 2f + dashBoardOffset,
            DASH_BOARD_RADIUS, dashBoardBackgroundPaint
        )

        dashBoardBackgroundPaint.xfermode = null

        canvas.restoreToCount(layerId)

        //绘制渐变背景
        if (velocityValue >= 180) {
            canvas.drawPath(shaderPath, shaderPaint)
        }

        //绘制 大刻度
        dashBoardScalePaint.pathEffect = scalePathDashPathEffect
        canvas.drawPath(dashBoardScalePath, dashBoardScalePaint)
        //绘制 小刻度
        dashBoardScalePaint.pathEffect = smallScalePathDashPathEffect
        canvas.drawPath(dashBoardScalePath, dashBoardScalePaint)

        //绘制文字数值
        for (index in 0 until DASH_BOARD_SCALE_SIZE) {
            val value = (index * 20).toString()

            if (index >= 9) {
                velocityValuePaint.color = resources.getColor(R.color.colorAccent)
            } else {
                velocityValuePaint.color = Color.WHITE
            }

            canvas.drawTextOnPath(
                (index * 20).toString(),
                velocityValuePath,
                index * scaleInterval - value.length * 11f.dp2Px() / 2f,
                0f,
                velocityValuePaint
            )
        }

        //绘制 指针

        val length = DASH_BOARD_RADIUS - 10f.dp2Px()

        val radians = Math.toRadians(
            (DASH_BOARD_START_ANGLE + (360 - 120) * velocityValue / 220).toDouble()
        )

        canvas.drawLine(
            (width / 2f + length / 4 * cos(radians)).toFloat(),
            (height / 2f + dashBoardOffset + length / 4 * sin(radians)).toFloat(),
            (width / 2f + length * cos(radians)).toFloat(),
            (height / 2f + dashBoardOffset + length * sin(radians)).toFloat(),
            pointerPaint
        )


        //绘制速度
        if (velocityValue >= 180) {
            velocityPaint.color = resources.getColor(R.color.colorAccent)
        } else {
            velocityPaint.color = Color.WHITE
        }
        canvas.drawText(
            "${velocityValue.toInt()}Km/h",
            width / 2f,
            height / 2f + 4 * DASH_BOARD_BACKGROUND_RADIUS / 5,
            velocityPaint
        )

    }


}