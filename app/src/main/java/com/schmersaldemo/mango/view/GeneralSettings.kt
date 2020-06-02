package com.schmersaldemo.mango.view

import android.os.Bundle
import android.view.MenuItem
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.biometric.AuthenticationListener
import com.schmersaldemo.mango.biometric.DeviceAuthPrompt
import com.schmersaldemo.mango.biometric.DeviceAuthenitcation
import com.schmersaldemo.mango.settings.Preferences
import kotlinx.android.synthetic.main.activity_general_settings.*

/*
* Author: Puneet Bahuguna
* */
class GeneralSettings : DeviceAuthPrompt(),AuthenticationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general_settings)
        supportActionBar!!.title=getString(R.string.setting)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        switchsetting.isChecked=Preferences.getDeviceLockSetting(this)
        switchsetting.setOnClickListener {
           //call for a device lock dialog here
            DeviceAuthenitcation(this).authenticate(this@GeneralSettings)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                super.onBackPressed()
            }
        }
        return true
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        switchsetting.isChecked=Preferences.getDeviceLockSetting(this)
    }

    override fun onAuthenticationSucceeded() {
        Preferences.setDeviceLockSetting(this,switchsetting.isChecked)
    }

    override fun onAuthenticationFailed() {
        switchsetting.isChecked=Preferences.getDeviceLockSetting(this)
    }
}
