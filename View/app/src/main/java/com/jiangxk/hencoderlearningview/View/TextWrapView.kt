package com.jiangxk.hencoderlearningview.View

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.extension.dp2Px

/**
 * @description com.jiangxk.hencoderlearningview.View
 * @author jiangxk
 * @time 2020-05-29  17:24
 */
class TextWrapView : View {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private val text = "2003年10月，Andy Rubin等人创建Android公司，并组建Android团队。\n" +
            "2005年8月17日，Google低调收购了成立仅22个月的高科技企业Android及其团队。安迪鲁宾成为Google公司工程部副总裁，继续负责Android项目。\n" +
            "2007年11月5日，谷歌公司正式向外界展示了这款名为Android的操作系统，并且在这天谷歌宣布建立一个全球性的联盟组织，该组织由34家手机制造商、软件开发商、电信运营商以及芯片制造商共同组成，并与84家硬件制造商、软件开发商及电信营运商组成开放手持设备联盟（Open Handset Alliance）来共同研发改良Android系统，这一联盟将支持谷歌发布的手机操作系统以及应用软件，Google以Apache免费开源许可证的授权方式，发布了Android的源代码。 [2-3] \n" +
            "2008年，在GoogleI/O大会上，谷歌提出了AndroidHAL架构图，在同年8月18号，Android获得了美国联邦通信委员会（FCC）的批准，在2008年9月，谷歌正式发布了Android 1.0系统，这也是Android系统最早的版本。\n" +
            "2009年4月，谷歌正式推出了Android 1.5这款手机，从Android 1.5版本开始，谷歌开始将Android的版本以甜品的名字命名，Android 1.5命名为Cupcake（纸杯蛋糕）。该系统与Android 1.0相比有了很大的改进。\n" +
            "2009年9月，谷歌发布了Android 1.6的正式版，并且推出了搭载Android 1.6正式版的手机HTC Hero（G3），凭借着出色的外观设计以及全新的Android 1.6操作系统，HTC Hero（G3）成为当时全球最受欢迎的手机。Android 1.6也有一个有趣的甜品名称，它被称为Donut（甜甜圈）。\n" +
            "2010年2月，Linux内核开发者Greg Kroah-Hartman将Android的驱动程序从Linux内核“状态树”（“staging tree”）上除去，从此，Android与Linux开发主流将分道扬镳。在同年5月份，谷歌正式发布了Android 2.2操作系统。谷歌将Android 2.2操作系统命名为Froyo，翻译完名为冻酸奶。\n" +
            "2010年10月，谷歌宣布Android系统达到了第一个里程碑，即电子市场上获得官方数字认证的Android应用数量已经达到了10万个，Android系统的应用增长非常迅速。在2010年12月，谷歌正式发布了Android 2.3操作系统Gingerbread （姜饼）。\n" +
            "2011年1月，谷歌称每日的Android设备新用户数量达到了30万部，到2011年7月，这个数字增长到55万部，而Android系统设备的用户总数达到了1.35亿，Android系统已经成为智能手机领域占有量最高的系统。\n" +
            "2011年8月2日，Android手机已占据全球智能机市场48%的份额，并在亚太地区市场占据统治地位，终结了Symbian（塞班系统）的霸主地位，跃居全球第一。\n" +
            "2011年9月，Android系统的应用数目已经达到了48万，而在智能手机市场，Android系统的占有率已经达到了43%。继续在排在移动操作系统首位。谷歌将会发布全新的Android 4.0操作系统，这款系统被谷歌命名为Ice Cream Sandwich（冰激凌三明治）。\n" +
            "2012年1月6日，谷歌Android Market已有10万开发者推出超过40万活跃的应用，大多数的应用程序为免费。Android Market应用程序商店目录在新年首周周末突破40万基准，距离突破30万应用仅4个月。在2011年早些时候，Android Market从20万增加到30万应用也花了四个月。 [4] \n" +
            "2013年11月1日，Android4.4正式发布，从具体功能上讲，Android4.4提供了各种实用小功能，新的Android系统更智能，添加更多的Emoji表情图案，UI的改进也更现代，如全新的HelloiOS7半透明效果。\n" +
            "2015年，网络安全公司Zimperium研究人员警告，安卓(Android)存在“致命”安全漏洞,黑客发送一封彩信便能在用户毫不知情的情况下完全控制手机。 [5] \n" +
            "2018年10月，谷歌表示，将于2018年12月6日停止Android系统中的Nearby Notifications（附近通知）服务，因为Android用户收到太多的附近商家推销信息的垃圾邮件。 [6] \n" +
            "2020年3月，谷歌的Android安全公告中提到，新更新已经提供了CVE-2020-0069补丁来解决针对联发科芯片的一个严重安全漏洞。 [7]"

    private val textPaint = TextPaint().apply {
        textSize = 20.dp2Px()
        isAntiAlias = true
    }

    private val paint = Paint().apply {
        textSize = 20.dp2Px()
        isAntiAlias = true
    }
    private val bitmap = getAvatar(IMAGE_SIZE)
    private val fontMetrics = Paint.FontMetrics()

    private var imageLeft = IMAGE_LEFT
    private var imageTop = IMAGE_TOP

    private var lastX = 0f
    private var lastY = 0f

    companion object {
        val IMAGE_SIZE = 200.dp2Px()
        val IMAGE_LEFT = 100.dp2Px()
        val IMAGE_TOP = 100.dp2Px()
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
                if (isTouchAvatar(event.x, event.y)) {

                    if (lastX != 0f && lastY != 0f) {
                        imageLeft += event.x - lastX
                        imageTop += event.y - lastY
                        if (imageLeft < 0f) {
                            imageLeft = 0f
                        } else if (imageLeft + IMAGE_SIZE > width) {
                            imageLeft = width - IMAGE_SIZE
                        }
                        if (imageTop < 0f) {
                            imageTop = 0f
                        } else if (imageTop + IMAGE_SIZE > height) {
                            imageTop = height - IMAGE_SIZE
                        }
                        invalidate()
                    }

                    lastX = event.x
                    lastY = event.y
                }
            }
            MotionEvent.ACTION_UP -> {
                lastX = 0f
                lastY = 0f
            }
            MotionEvent.ACTION_CANCEL -> {
                lastX = 0f
                lastY = 0f
            }
        }

        return true
    }

    override fun onDraw(canvas: Canvas) {
//        StaticLayout.Builder.obtain(text, 0, text.length, textPaint, width)
//            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
//            .setLineSpacing(0f, 1f)
//            .setIncludePad(false)
//            .build().draw(canvas)

        canvas.drawBitmap(bitmap, imageLeft, imageTop, paint)

        paint.getFontMetrics(fontMetrics)
        val measureWidth = floatArrayOf(0f)

        var start = 0
        var count: Int
        var verticalOffset = -fontMetrics.top
        var maxWidth: Float

        while (start < text.length) {
            if (verticalOffset + fontMetrics.bottom < imageTop ||
                verticalOffset + fontMetrics.top > imageTop + IMAGE_SIZE
            ) {
                count =
                    paint.breakText(text, start, text.length, true, width.toFloat(), measureWidth)
                canvas.drawText(
                    text,
                    start,
                    start + count,
                    0f,
                    verticalOffset,
                    paint
                )
            } else {
                count = paint.breakText(text, start, text.length, true, imageLeft, measureWidth)
                canvas.drawText(
                    text,
                    start,
                    start + count,
                    0f,
                    verticalOffset,
                    paint
                )
                val oldCount = count
                count = paint.breakText(
                    text,
                    start + oldCount,
                    text.length,
                    true,
                    width - imageLeft - IMAGE_SIZE,
                    measureWidth
                ) + oldCount
                canvas.drawText(
                    text,
                    start + oldCount,
                    start + count,
                    imageLeft + IMAGE_SIZE,
                    verticalOffset,
                    paint
                )

            }

            start += count
            verticalOffset += paint.fontSpacing

        }

    }


    private fun getAvatar(width: Float): Bitmap {
        val options = BitmapFactory.Options()
            .apply {
                inJustDecodeBounds = true
                BitmapFactory.decodeResource(resources, R.drawable.lengtu, this)
                inJustDecodeBounds = false
                inDensity = outWidth
                inScaled = true
                inTargetDensity = width.toInt()
            }

        return BitmapFactory.decodeResource(resources, R.drawable.lengtu, options)
    }

    private fun isTouchAvatar(touchX: Float, touchY: Float): Boolean {
        if (touchX >= imageLeft && touchX <= imageLeft + IMAGE_SIZE &&
            touchY >= imageTop && touchY <= imageTop + IMAGE_SIZE
        ) {
            return true
        }
        return false
    }

}