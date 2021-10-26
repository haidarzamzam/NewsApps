package com.haidev.newsapps.util

import android.content.res.Resources

class Util {
    companion object {
        fun dpToPx(dp: Int): Int {
            return ((dp * Resources.getSystem().displayMetrics.density).toInt())
        }
    }
}