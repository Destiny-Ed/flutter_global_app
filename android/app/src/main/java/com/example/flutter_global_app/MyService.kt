package com.example.flutter_global_app

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import android.widget.LinearLayout

import android.view.WindowManager
import android.view.Gravity

import android.graphics.PixelFormat
import android.os.Handler
import android.util.Log
import java.util.*


class MyService : Service() , View.OnTouchListener {

    // window manager
    private var mWindowManager: WindowManager? = null

    // linear layout will use to detect touch event
    private var touchLayout: LinearLayout? = null

    val handler = Handler()

    override fun onCreate() {
        super.onCreate()

        //Create a timer that calls flutter every 5 seconds
        handler.postDelayed(object : Runnable {
            override fun run() {
                //Call your function here
//                Toast.makeText(baseContext, "Timer Running", Toast.LENGTH_SHORT).show()
                MainActivity.callFutter()
                handler.postDelayed(this, 5000 )//1 sec delay
            }
        }, 0)

        // create linear layout
        touchLayout = LinearLayout(this)
        // set layout width 30 px and height is equal to full screen
        val lp = WindowManager.LayoutParams(30, WindowManager.LayoutParams.MATCH_PARENT)
        touchLayout!!.layoutParams = lp
        // set color if you want layout visible on screen
		touchLayout!!.setBackgroundColor(Color.CYAN);
        // set on touch listener
        touchLayout!!.setOnTouchListener(this)

        // fetch window manager object
        mWindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        // set layout parameter of window manager
        val mParams = WindowManager.LayoutParams(
                30,  // width of layout 30 px
                WindowManager.LayoutParams.MATCH_PARENT,  // height is equal to full screen
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,  // Type Ohone, These are non-application windows providing user interaction with the phone (in particular incoming calls).
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  // this window won't ever get key input focus
                PixelFormat.TRANSLUCENT)
        mParams.gravity = Gravity.LEFT or Gravity.TOP
        mWindowManager!!.addView(touchLayout, mParams)
    }
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {

        if(p1!!.action == MotionEvent.ACTION_UP || p1!!.action == MotionEvent.ACTION_DOWN){
            Toast.makeText(this, "Touch occurred", Toast.LENGTH_SHORT).show()
            MainActivity.callFutter()
        }

        return true
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}