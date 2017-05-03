package `in`.mvpstarter.sample.runner

import android.annotation.SuppressLint
import android.app.Application
import android.app.KeyguardManager
import android.content.Context.KEYGUARD_SERVICE
import android.content.Context.POWER_SERVICE
import android.os.PowerManager
import android.os.PowerManager.*
import android.support.test.runner.AndroidJUnitRunner

open class UnlockDeviceAndroidJUnitRunner : AndroidJUnitRunner() {

    private var mWakeLock: PowerManager.WakeLock? = null

    @SuppressLint("MissingPermission")
    override fun onStart() {
        val application = targetContext.applicationContext as Application
        val simpleName = UnlockDeviceAndroidJUnitRunner::class.java.simpleName
        // Unlock the device so that the tests can input keystrokes.
        (application.getSystemService(KEYGUARD_SERVICE) as KeyguardManager)
                .newKeyguardLock(simpleName)
                .disableKeyguard()
        // Wake up the screen.
        val powerManager = application.getSystemService(POWER_SERVICE) as PowerManager
        mWakeLock = powerManager.newWakeLock(FULL_WAKE_LOCK or ACQUIRE_CAUSES_WAKEUP or
                ON_AFTER_RELEASE, simpleName)
        mWakeLock!!.acquire()
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        mWakeLock!!.release()
    }
}