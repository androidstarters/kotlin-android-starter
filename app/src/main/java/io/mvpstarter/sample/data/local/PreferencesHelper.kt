package io.mvpstarter.sample.data.local

import io.mvpstarter.sample.injection.ApplicationContext
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferencesHelper @Inject
constructor(@ApplicationContext context: Context) {

    private val mPref: SharedPreferences

    init {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun clear() {
        mPref.edit().clear().apply()
    }

    companion object {

        val PREF_FILE_NAME = "mvpstarter_pref_file"
    }

}
