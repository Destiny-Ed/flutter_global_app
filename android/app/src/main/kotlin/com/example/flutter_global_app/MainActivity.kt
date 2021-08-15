package com.example.flutter_global_app

import android.content.Intent
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : Flutter() {


    private var CHANNEL = "dest"


    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        flutterEngin = flutterEngine


        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->

            if (call.method == "Start_service") {
                //Start Service
                val service_intent = Intent(this, MyService::class.java)
                startService(service_intent)

                result.success("Service Started")
            }
        }

    }


    companion object {
        private lateinit var flutterEngin: FlutterEngine
        fun callFutter() {
            MethodChannel(flutterEngin.dartExecutor.binaryMessenger,
                    "CallFlutter").invokeMethod("showToast", "")
        }
    }
}
