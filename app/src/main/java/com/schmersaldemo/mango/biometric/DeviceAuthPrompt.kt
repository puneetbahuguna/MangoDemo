package com.schmersaldemo.mango.biometric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricConstants
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.schmersaldemo.mango.localization.BaseActivity
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.view.AddLoginAccount

open class DeviceAuthPrompt : BaseActivity(), LifecycleObserver,AuthenticationListener {

    var isWindowFocused = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onAppBackgrounded() {
        isWindowFocused = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onAppForegrounded() {
        if (!isWindowFocused) {
            if(Preferences.getDeviceLockSetting(this))
           DeviceAuthenitcation(this).authenticate(this@DeviceAuthPrompt)
        }
    }
    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        if(errorCode==BiometricConstants.ERROR_USER_CANCELED){
            DeviceAuthenitcation(this).authenticate(this@DeviceAuthPrompt)
        }else if(errorCode==BiometricConstants.ERROR_TIMEOUT ||errorCode==BiometricConstants.ERROR_LOCKOUT){
            Intent().setClass(this, AddLoginAccount::class.java)
            this@DeviceAuthPrompt.finish()
        }
    }

    override fun onAuthenticationSucceeded() {

    }

    override fun onAuthenticationFailed() {
        Intent().setClass(this, AddLoginAccount::class.java)
        this@DeviceAuthPrompt.finish()
    }
}
