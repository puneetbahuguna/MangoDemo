package com.schmersaldemo.mango.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.data.entity.*
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.settings.Utils
import com.schmersaldemo.mango.view.viewmodels.SyncViewModel

/***
Author: Puneet Bahuguna
 ***/

class Splash : AppCompatActivity() {

    private lateinit var syncViewModel: SyncViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //Utils.setLocale(this@Splash,Preferences.getLangCode(this@Splash))
        syncViewModel=ViewModelProvider(this).get(SyncViewModel::class.java)
        if(!Preferences.getDataSaved(this)){
            insertLocaldata()
        }
        Handler().postDelayed(Runnable {
            //startActivity(Intent().setClass(this,Login::class.java))
            startActivity(Intent().setClass(this,AddLoginAccount::class.java))
            this@Splash.finish()
        },3000)
    }

    fun insertLocaldata(){
        syncViewModel.insertUser(User(1	,"Puneet","123",1,"India","",""))
    //Insert user role
        val userRole = listOf(UserRole(1,"Global Admin","Administrador global"),
            UserRole(2,"Local Admin","Administrador local"), UserRole(3,"Consulting","Consultando"),
            UserRole(4,"BackOffice","BackOffice"),UserRole(5,"Manager","Gerente"), UserRole(6,"Inactive","Inativo"))
        //insert customers

        val customerList= listOf(Customer(1,"ABC Corp","Address1","City 1","India","State 1","",""),
                Customer(2,"Schmersal","Address2","City 2","India","State 1","",""),
                Customer(3,"Incedo inc","Address3","City 3","India","State 1","",""),
                Customer(4,"Tech Nicum","Address4","City 4","India","State 1","",""))
        //insert customer mapping
        val mappingList = listOf(CustomerMapping(1,1,1),CustomerMapping(2,2,1),CustomerMapping(3,2,1),CustomerMapping(4,3,1),CustomerMapping(5,3,2),
            CustomerMapping(6,3,3),CustomerMapping(7,4,1),CustomerMapping(8,4,2),CustomerMapping(9,4,3),CustomerMapping(10,4,4))

        //insert language list
        val lanList = listOf(LanguageList(1,"ES-EN","ENGLISH"), LanguageList(2,"PT-BR","BRAZIALIAN PORTUGUESE"))
        syncViewModel.insertUserRole(userRole)
        syncViewModel.insertCustomer(customerList)
        syncViewModel.insertCustomerMapping(mappingList)
        syncViewModel.insertLanguageList(lanList)
        Preferences.setDataSaved(this,true)
    }
}
