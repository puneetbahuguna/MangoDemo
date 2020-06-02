package com.schmersaldemo.mango.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.trimmedLength
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.localization.BaseActivity
import com.schmersaldemo.mango.localization.LocaleManager
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.settings.Utils
import com.schmersaldemo.mango.view.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_add_login_account.*
import kotlinx.coroutines.launch

/***
Author: Puneet Bahuguna
 ***/

class AddLoginAccount : BaseActivity(), View.OnClickListener {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userSpinner:Spinner
    private lateinit var languageSpinner:Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_login_account)
        ok.setOnClickListener(this)
        addacc.setOnClickListener(this)
        exstlogin.setOnClickListener(this)
        loginViewModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        userSpinner = findViewById(R.id.userSpinner)
        languageSpinner=findViewById(R.id.languageSpinner)
        lifecycleScope.launch {
            val userList = loginViewModel.getAllLoginUsers()
            val langCodes = loginViewModel.getLanguageCodes()
            val userAdapter = ArrayAdapter(this@AddLoginAccount,android.R.layout.simple_spinner_item,userList)
            userAdapter.setDropDownViewResource(R.layout.dropdown_item)
            userSpinner.adapter=userAdapter
            val langAdapter = ArrayAdapter(this@AddLoginAccount,android.R.layout.simple_spinner_item,langCodes)
            langAdapter.setDropDownViewResource(R.layout.dropdown_item)
            languageSpinner.adapter=langAdapter
            languageSpinner.setSelection(Preferences.getselectedLang(this@AddLoginAccount))
        }
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("Tag","mesage")
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position!=Preferences.getselectedLang(this@AddLoginAccount)){
                    Preferences.setselectedLang(this@AddLoginAccount,position)
                    Preferences.setLangCode(this@AddLoginAccount,languageSpinner.selectedItem.toString())
                    if(languageSpinner.selectedItem.toString().equals("ES-EN")){
                        Utils.setNewLocale(this@AddLoginAccount,LocaleManager.LANGUAGE_ENGLISH,false)
                    }else{
                        Utils.setNewLocale(this@AddLoginAccount,LocaleManager.LANGUAGE_PURTTEGESE,false)
                    }
                }
            }
        }
    }
    override fun onClick(p0: View?) {
        when(p0!!.id){
           R.id.ok->{
             changeView()
           }
            R.id.addacc->{
             startActivity(Intent().setClass(this,Login::class.java))
            }
            R.id.exstlogin->{
                if(loggedinpassword.text!!.trimmedLength()<1){
                    Toast.makeText(this,getString(R.string.enterpwd),Toast.LENGTH_SHORT).show()
                }else doLocalLogin(userSpinner.selectedItem.toString(),loggedinpassword.text.toString())
            }
        }
    }
    fun doLocalLogin(username:String,password:String){
        lifecycleScope.launch {
            val user=loginViewModel.authenticateLocaluser(username,password)
            if(user!=null){
                Preferences.setUsername(this@AddLoginAccount,user.username.toString())
                Preferences.setuserRoleID(this@AddLoginAccount,user.role_id!!.toInt())
                Preferences.setUserLogged(this@AddLoginAccount,true)
                startActivity(Intent().setClass(this@AddLoginAccount,MainActivity::class.java))
                this@AddLoginAccount.finish()
            }else{
                Toast.makeText(this@AddLoginAccount,getString(R.string.pwderror), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    override fun onBackPressed() {
        if(btnlayout.visibility==View.INVISIBLE){
            loggedinpassword.visibility=View.INVISIBLE
            exstlogin.visibility=View.INVISIBLE
            btnlayout.visibility=View.VISIBLE
            languageSpinner.visibility=View.VISIBLE
        }else{
            //super.onBackPressed()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            //finish()

        }
    }
    fun changeView(){
        loggedinpassword.visibility=View.VISIBLE
        exstlogin.visibility=View.VISIBLE
        btnlayout.visibility=View.INVISIBLE
        languageSpinner.visibility=View.INVISIBLE
    }
}