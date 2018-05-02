package me.crazystone.study.wanandroid.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by crazy_stone on 18-4-20.
 */
object Toasts {

    fun toast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}