package com.anwesome.ui.bottomnavigationbarkotlindemo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesome.ui.bottomnavigationbarkotlin.BottomNavigationBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bitmap = BitmapFactory.decodeResource(resources,R.drawable.js)
        BottomNavigationBar.create(this)
        for(i in 0..5) {
            BottomNavigationBar.addButton(bitmap)
        }
        BottomNavigationBar.show(this)
    }
}
