package com.example.todo_list_sample.utils

import android.icu.text.SimpleDateFormat

class DateUtils {
    companion object {
        val formatter by lazy { SimpleDateFormat("EEE, d MMM yyyy HH:mm a") }
    }
}