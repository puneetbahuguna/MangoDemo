package com.schmersaldemo.mango.biometric

import android.content.Context
import android.content.res.TypedArray
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.schmersaldemo.mango.R

class DeviceAuthenitcation(authlistener: AuthenticationListener) {
    var authlistener: AuthenticationListener? = null
    init {
        this.authlistener=authlistener
    }
    fun authenticate(context:Context) {
        val executor = ContextCompat.getMainExecutor(context)
        val biometricPrompt = BiometricPrompt(context as FragmentActivity,
            executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    authlistener!!.onAuthenticationError(errorCode,errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    authlistener!!.onAuthenticationSucceeded()
                }

                override fun onAuthenticationFailed() {
                    authlistener!!.onAuthenticationFailed()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(context.getString(R.string.biotitle))
            .setSubtitle(context.getString(R.string.biomsg))
            .setDeviceCredentialAllowed(true)
            //.setNegativeButtonText("Use account password")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}
