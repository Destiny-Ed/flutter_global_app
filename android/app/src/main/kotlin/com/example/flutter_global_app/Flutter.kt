package com.example.flutter_global_app

import android.content.Context
import android.content.Intent
import io.flutter.embedding.android.FlutterActivity

open class Flutter : FlutterActivity() {override fun onDestroy() {
//    flutterEngine?.platformViewsController?.onFlutterViewDestroyed()
    flutterEngine!!.platformViewsController.onPreEngineRestart()
    createIntent(this)
    super.onDestroy()
}companion object {
    fun createIntent(context: Context, initialRoute: String = "/"): Intent {
        return Intent(context, Flutter::class.java)
                .putExtra("initial_route", initialRoute)
                .putExtra("background_mode", "opaque")
                .putExtra("destroy_engine_with_activity", true)
    }
}
}