package com.anwesome.ui.bottomnavigationbarkotlin

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.widget.Button

/**
 * Created by anweshmishra on 11/08/17.
 */
class BottomNavigationBarButton(ctx:Context,var bitmap: Bitmap,var controller: ButtonController):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val renderer = Renderer()
    override fun onDraw(canvas: Canvas) {
        renderer.render(canvas,paint,this)
    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                controller.startUpdating(this)
            }
        }
        return true
    }
    fun update(factor:Float) {
        renderer.update(factor)
        postInvalidate()
    }
    data class ButtonShape(var bitmap: Bitmap,var x:Float,var y:Float,var r:Float) {
        fun draw(canvas: Canvas,paint:Paint,scale:Float) {
            paint.color = Color.BLACK
            var newScale = 0.5f+0.5f*scale
            canvas.save()
            canvas.translate(x,y)
            canvas.scale(newScale,newScale)
            var path = Path()
            path.addCircle(0.0f,0.0f,r,Path.Direction.CW)
            canvas.clipPath(path)
            canvas.drawBitmap(bitmap,-bitmap.width.toFloat()/2,-bitmap.height.toFloat()/2,paint)
            paint.color = Color.argb((200*scale).toInt(),3,169,244)
            canvas.drawCircle(0.0f,0.0f,r,paint)
            canvas.restore()
        }
    }
    class Renderer {
        var time = 0
        var scale = 0.0f
        var btnShape:ButtonShape?=null
        fun render(canvas:Canvas,paint:Paint,v:BottomNavigationBarButton) {
            if(time == 0) {
                var iw = canvas.width
                var ih = canvas.height
                var w = iw.toFloat()
                var h = ih.toFloat()
                var r = Math.min(w,h)/2
                var bitmap = Bitmap.createScaledBitmap(v.bitmap,iw,ih,true)
                btnShape = ButtonShape(bitmap,w/2,h/2,r)
            }
            btnShape?.draw(canvas,paint,scale)
        }
        fun update(scale:Float) {
            this.scale = scale
        }
    }
}