package io.mvpstarter.sample.data.model

import com.squareup.moshi.Json

class Statistic {
    var stat: NamedResource? = null
    @Json(name="base_stat")
    var baseStat: Int = 0
}