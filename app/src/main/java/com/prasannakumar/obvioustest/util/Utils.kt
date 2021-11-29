package com.prasannakumar.obvioustest.util

import android.app.Activity
import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.io.IOException

class Utils(private var context: Context) {
    fun getJsonFromAssets(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getCircle(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}