package com.anwesome.ui.bottomnavigationbarkotlin

/**
 * Created by anweshmishra on 11/08/17.
 */
class ButtonController {
    var prev:BottomNavigationBarButton?=null
    var curr:BottomNavigationBarButton?=null
    var animated = false
    var state = State()
    fun update() {
        if(animated) {
            try {
                state.update()
                if(state.stopped()) {
                    animated = false
                    prev = curr
                }
                Thread.sleep(50)
                curr?.update(state.scale)
                prev?.update(state.scale)
            }
            catch (ex:Exception) {

            }
        }
    }
    fun startUpdating(button:BottomNavigationBarButton) {
        curr = button
    }
    class State {
        var scale:Float = 0.0f
        fun update() {
            scale += 0.2f
        }
        fun stopped():Boolean {
            if(scale > 1.0f) {
                scale = 0.0f
                return true
            }
            return false
        }
    }
}