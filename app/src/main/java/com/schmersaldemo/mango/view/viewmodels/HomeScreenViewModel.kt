package com.schmersaldemo.mango.view.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.schmersaldemo.mango.components.CustomerListRepository
import com.schmersaldemo.mango.data.database.AppDatabase
import com.schmersaldemo.mango.data.entity.Customer

class HomeScreenViewModel(application: Application):AndroidViewModel(application){
    val customerListDao = AppDatabase.getDatabase(application).customerDao()

    suspend fun getCustomerList(roleid:Int):List<Customer>{
        return CustomerListRepository(customerListDao).getCustomerList(roleid)
    }
     suspend fun getUserRole(roleid: Int):String{
        return CustomerListRepository(customerListDao).getUserRole(roleid)
    }
    suspend fun getUserRolePR(roleid: Int):String{
        return CustomerListRepository(customerListDao).getUserRolePR(roleid)
    }
}