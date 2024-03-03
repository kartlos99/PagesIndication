package com.example.viewpagerpagesindication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

class PagesIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val gapSize by lazy { 20f }

    var blockCount = 1
        set(value) {
            field = value
            blockSize = (this.width - gapSize * (value - 1)) / value
        }

    private var blockSize = 1f

    private val lineWidth = 20f

    var fillPoint = 0f

    private val posY = 20f

    private val basePaint by lazy {
        Paint().apply {
            color = Color.GRAY
            strokeWidth = lineWidth
        }
    }

    private val fillPaint by lazy {
        Paint().apply {
            color = Color.GREEN
            strokeWidth = lineWidth
        }
    }

    init {
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        blockSize = (this.width - gapSize * (blockCount - 1)).div(blockCount)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var startX = 0f
        var endX = blockSize

        (0..(blockCount - 1)).forEach { index ->
            startX = (blockSize + gapSize) * index
            endX = startX + blockSize
            canvas.drawLine(startX, posY, endX, posY, basePaint)
            canvas.drawRoundRect(startX, posY * 2, endX, posY * 2 + lineWidth, 10f, 10f, basePaint)
        }

        var index = 0
        do {
            startX = (blockSize + gapSize) * index
            endX = startX + blockSize
            canvas.drawLine(startX, posY, endX, posY, fillPaint)
            canvas.drawRoundRect(startX, posY * 2, endX, posY * 2 + lineWidth, 10f, 10f, fillPaint)
            index++
        } while (index <= fillPoint)

        val fraction = fillPoint.mod(1f)

        startX = (blockSize + gapSize) * index
        endX = startX + blockSize * fraction
        canvas.drawLine(startX, posY, endX, posY, fillPaint)
        canvas.drawRoundRect(startX, posY * 2, endX, posY * 2 + lineWidth, 10f, 10f, fillPaint)
    }

    fun setupWithPager(pager: ViewPager) {

        blockCount = pager.adapter?.count ?: 1

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                fillPoint = position + positionOffset
                invalidate()
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

        })
    }

}

