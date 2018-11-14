package com.example.asset.todoproject.helpers

import android.util.Log
import com.example.asset.todoproject.BuildConfig

class Logger {
    companion object {
        fun msg(msg: Any) {
            if (BuildConfig.DEBUG) {
                Log.i("MSG", msg.toString() + "")
            }
        }

        fun api(msg: String) {
            if (BuildConfig.DEBUG) {
                Log.i("API", msg)
            }
        }

        fun msg(tag: String, msg: Any) {
            if (BuildConfig.DEBUG) {
                Log.i(tag, msg.toString() + "")
            }
        }
    }
}