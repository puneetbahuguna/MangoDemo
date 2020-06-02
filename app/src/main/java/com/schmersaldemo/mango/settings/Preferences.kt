package com.schmersaldemo.mango.settings

import android.content.Context
import android.content.SharedPreferences

import androidx.preference.PreferenceManager

/*
 * @Author:Puneet Bahuguna
 * @Year:22-11-2019-20
 * @Description:This class saves all app related shared preferences.*/

object Preferences {
    private val DATA_SAVE_FLAG = "datasave"
    private val USERROLE_ID="roleid"
    private val USERNAME="username"
    private val LANGCODE="langcode"
    private val LANGPOSITION="langposition"
    private val ISLOGGED="islogged"
    private val ISLOCKED="islocked"


    private fun readInt(_context: Context, key: String, defaultValue: Int): Int {
        val pref = PreferenceManager.getDefaultSharedPreferences(_context)
        return pref.getInt(key, defaultValue)
    }

    private fun writeInt(_context: Context, key: String, value: Int) {
        val settings = PreferenceManager.getDefaultSharedPreferences(_context)
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun readString(_context: Context, key: String, defaulValue: String?): String? {

        val pref = PreferenceManager.getDefaultSharedPreferences(_context)
        return pref.getString(key, defaulValue)
    }

    private fun writeString(_context: Context, key: String, value: String) {
        val spref = PreferenceManager.getDefaultSharedPreferences(_context)
        val editor = spref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun readBoolean(_context: Context, key: String, defaultValue: Boolean): Boolean {
        val settings = PreferenceManager.getDefaultSharedPreferences(_context)
        return settings.getBoolean(key, defaultValue)
    }

    private fun writeBoolean(_context: Context, key: String, value: Boolean) {
        val settings = PreferenceManager.getDefaultSharedPreferences(_context)
        val editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun clearPreferences(mContext: Context) {
        PreferenceManager.getDefaultSharedPreferences(mContext).edit().clear().apply()
    }

    fun getuserRoleID(_context: Context): Int {
        return readInt(_context, USERROLE_ID, -1)
    }

    fun setuserRoleID(_context: Context, roleID: Int) {
        writeInt(_context, USERROLE_ID, roleID)
    }

    fun getselectedLang(_context: Context): Int {
        return readInt(_context, LANGPOSITION, 0)
    }

    fun setselectedLang(_context: Context, position: Int) {
        writeInt(_context, LANGPOSITION, position)
    }
    fun getDataSaved(_context: Context): Boolean {
        return readBoolean(_context, DATA_SAVE_FLAG, false)
    }
    fun setDataSaved(_context: Context, flag: Boolean) {
        writeBoolean(_context, DATA_SAVE_FLAG, flag)
    }
    fun getUserLogged(_context: Context): Boolean {
        return readBoolean(_context, ISLOGGED, false)
    }
    fun setUserLogged(_context: Context, flag: Boolean) {
        writeBoolean(_context, ISLOGGED, flag)
    }
    fun getUsername(_context: Context): String? {
        return readString(_context, USERNAME, null)
    }
    fun setUsername(_context: Context, username: String) {
        writeString(_context, USERNAME, username)
    }
    fun getLangCode(_context: Context): String? {
        return readString(_context, LANGCODE, "ES-EN")
    }
    fun setLangCode(_context: Context, langcode: String) {
        writeString(_context, LANGCODE, langcode)
    }
    fun getDeviceLockSetting(_context: Context): Boolean {
        return readBoolean(_context, ISLOCKED, false)
    }
    fun setDeviceLockSetting(_context: Context, flag: Boolean) {
        writeBoolean(_context, ISLOCKED, flag)
    }
}
