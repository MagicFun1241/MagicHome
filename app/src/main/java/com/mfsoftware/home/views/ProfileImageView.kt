package com.mfsoftware.home.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF

class ProfileImageView(context: Context?) : androidx.appcompat.widget.AppCompatImageView(context) {
    private val radius = 18.0f
    private var path: Path? = null
    private var rect: RectF? = null

    init {
        path = Path()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        rect = RectF(0F, 0F, this.width.toFloat(), this.height.toFloat())
        path!!.addRoundRect(rect!!, radius, radius, Path.Direction.CW)
        canvas.clipPath(path!!)
        super.onDraw(canvas)
    }
}