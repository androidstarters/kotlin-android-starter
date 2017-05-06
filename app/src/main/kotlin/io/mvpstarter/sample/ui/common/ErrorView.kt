package io.mvpstarter.sample.ui.common

import io.mvpstarter.sample.R
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import butterknife.ButterKnife
import butterknife.OnClick

class ErrorView : LinearLayout {

    private var mErrorListener: ErrorListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        LayoutInflater.from(context).inflate(R.layout.view_error, this)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.button_reload)
    fun onReloadButtonClick() {
        if (mErrorListener != null) {
            mErrorListener!!.onReloadData()
        }
    }

    fun setErrorListener(errorListener: ErrorListener) {
        mErrorListener = errorListener
    }

    interface ErrorListener {
        fun onReloadData()
    }
}
