package com.github.brokenithm.activity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class ZoneOverlayView(context: Context) : View(context) {

    private var zoneStates = BooleanArray(6) { false }
    private var roiCenterX = 0.5f
    private var roiWidth = 0.3f
    private var roiSpacing = 0.02f

    private val activePaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }
    private val inactivePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }

    fun updateZones(states: BooleanArray, centerX: Float, width_: Float, spacing: Float) {
        zoneStates = states.copyOf()
        roiCenterX = centerX
        roiWidth = width_
        roiSpacing = spacing
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val roiWidthPx = roiWidth * width
        val roiLeft = roiCenterX * width - roiWidthPx / 2f
        val roiRight = roiLeft + roiWidthPx

        val spacingPx = roiSpacing * height
        val totalSpacing = spacingPx * 5f
        val zoneHeight = (height - totalSpacing) / 6f

        for (i in 0 until 6) {
            val top = i * (zoneHeight + spacingPx)
            val bottom = top + zoneHeight
            val paint = if (zoneStates[i]) activePaint else inactivePaint
            canvas.drawRect(roiLeft + 4f, top + 4f, roiRight - 4f, bottom - 4f, paint)
        }
    }
}