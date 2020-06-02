package com.schmersaldemo.mango.biometric

/*
* Author: Puneet Bahuguna
* */
interface AuthenticationListener{
    fun onAuthenticationError(errorCode:Int, errString:CharSequence)
    fun onAuthenticationSucceeded()
    fun onAuthenticationFailed()
}