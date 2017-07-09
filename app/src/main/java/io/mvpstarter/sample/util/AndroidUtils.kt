package io.mvpstarter.sample.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by shivam on 8/7/17.
 */

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}