package com.sousnein.lethaljokes

import android.content.Context
import android.view.inputmethod.InputMethodManager

lateinit var APP_ACTIVITY: MainActivity


fun hideKeyboard() {
    val imm: InputMethodManager = APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    imm.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken, 0)
}