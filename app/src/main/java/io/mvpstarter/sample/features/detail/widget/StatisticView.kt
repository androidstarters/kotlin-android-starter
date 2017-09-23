package io.mvpstarter.sample.features.detail.widget

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import io.mvpstarter.sample.R
import io.mvpstarter.sample.data.model.Statistic

class StatisticView : RelativeLayout {

    @BindView(R.id.pokemon_name) @JvmField var nameText: TextView? = null
    @BindView(R.id.progress_stat) @JvmField var statProgress: ProgressBar? = null

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
        LayoutInflater.from(context).inflate(R.layout.view_statistic, this)
        ButterKnife.bind(this)
    }

    @SuppressLint("SetTextI18n")
    fun setStat(statistic: Statistic) {
        nameText?.text = "${statistic.stat?.name?.substring(0, 1)?.toUpperCase()}${statistic.stat?.name?.substring(1)}"
        statProgress?.progress = statistic.baseStat
    }
}
