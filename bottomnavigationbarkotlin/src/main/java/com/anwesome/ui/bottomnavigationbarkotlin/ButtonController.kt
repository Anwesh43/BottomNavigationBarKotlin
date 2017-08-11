package com.anwesome.ui.bottomnavigationbarkotlin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator

/**
 * Created by anweshmishra on 11/08/17.
 */
class ButtonController:AnimatorListenerAdapter(),ValueAnimator.AnimatorUpdateListener {
    var prev:BottomNavigationBarButton?=null
    var curr:BottomNavigationBarButton?=null
    var animated = false
    var startAnim = ValueAnimator.ofFloat(0.0f,1.0f)
    init {
        startAnim.addUpdateListener(this)
        startAnim.addListener(this)
        startAnim.duration = 500
    }
    override fun onAnimationUpdate(vf:ValueAnimator) {
        var factor = vf.animatedValue as Float
        curr?.update(factor)
        prev?.update(1-factor)
    }
    override fun onAnimationEnd(animator:Animator) {
        if(animated) {
            animated = false
            prev = curr
        }
    }
    fun startUpdating(button:BottomNavigationBarButton) {
        if(!animated) {
            curr = button
            animated = true
            startAnim.start()
        }
    }
}