package com.example.bookmyshow.Utility

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object DisplayUtility {

    fun toastMessage(contex: Context, message: String) {
        val toast: Toast = Toast.makeText(contex, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}