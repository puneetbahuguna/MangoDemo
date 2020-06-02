package com.schmersaldemo.mango.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.localization.BaseActivity
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.settings.Utils
import com.schmersaldemo.mango.view.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch

/***
Author: Puneet Bahuguna
 ***/

class Login : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        login.setOnClickListener {
            doLogin(loginusername.text.toString(),loginpassword.text.toString())
        }
    }

    override fun onBackPressed() {
        if(!isTaskRoot) {
           super.onBackPressed()
        }else {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
    fun doLogin(username:String,password:String){
        lifecycleScope.launch {
            val user=loginViewModel.authenticateLocaluser(username,password)
            if(user!=null){
                Preferences.setUsername(this@Login,user.username.toString())
                Preferences.setuserRoleID(this@Login,user.role_id!!.toInt())
                Preferences.setUserLogged(this@Login,true)
                startActivity(Intent().setClass(this@Login,MainActivity::class.java))
                this@Login.finish()
            }else{
                Toast.makeText(this@Login,getString(R.string.autherror),Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
