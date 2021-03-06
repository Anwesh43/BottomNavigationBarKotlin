package com.anwesome.ui.bottomnavigationbarkotlin

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.HorizontalScrollView

/**
 * Created by anweshmishra on 11/08/17.
 */
class BottomNavigationBar(ctx:Context):ViewGroup(ctx) {
    var w:Int = 0
    var h:Int = 0
    var hSize:Int = 0
    var controller = ButtonController()
    init {
        initWH(ctx)
    }
    private fun initWH(ctx:Context) {
        var displayManager = ctx.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        var display = displayManager.getDisplay(0)
        var size = Point()
        display.getRealSize(size)
        w = size.x
        h = size.y
        hSize = h/8
    }
    override fun onMeasure(wspec:Int,hspec:Int) {
        var wView = 0
        for(i in 0..childCount-1) {
            var child = getChildAt(i)
            measureChild(child,wspec,hspec)
            wView += child.measuredWidth + w/12
        }
        setMeasuredDimension(wView,hSize)
    }
    override fun onLayout(change:Boolean,a:Int,b:Int,wv:Int,hv:Int) {
        var x = 0
        for(i in 0..childCount-1) {
            var child = getChildAt(i)
            child.layout(x,h/30,x+child.measuredWidth,h/30+child.measuredHeight)
            x += child.measuredWidth + w/12
        }

    }
    fun addButton(bitmap:Bitmap) {
        var button = BottomNavigationBarButton(context,bitmap,controller)
        addView(button, LayoutParams(h/12,h/12))
    }
    companion object {
        var viewCreated = false
        var bar:BottomNavigationBar?=null
        fun create(activity: Activity) {
            if(!viewCreated) {
                bar = BottomNavigationBar(activity)
                viewCreated = true
            }
        }
        fun addButton(bitmap: Bitmap) {
            bar?.addButton(bitmap)
        }
        fun hideActionBars(activity: Activity) {
            if(activity is AppCompatActivity) {
                var actionBar = activity.supportActionBar
                actionBar?.hide()
            }
            else {
                var actionBar = activity.actionBar
                actionBar?.hide()
            }
        }
        fun showAsFullScreen(activity: Activity) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        fun show(activity: Activity) {
            if(viewCreated) {
                hideActionBars(activity)
                showAsFullScreen(activity)
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                bar?.setBackgroundColor(Color.parseColor("#9E9E9E"))
                var scrollView = HorizontalScrollView(activity)
                scrollView.addView(bar, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
                scrollView.y = (bar?.h?:0) - (bar?.hSize?:0).toFloat()
                activity.addContentView(scrollView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
            }
        }
    }
}