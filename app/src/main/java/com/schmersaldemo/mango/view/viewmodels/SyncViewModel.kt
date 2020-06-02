package com.schmersaldemo.mango.view.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.schmersaldemo.mango.components.SyncRepository
import com.schmersaldemo.mango.data.database.AppDatabase
import com.schmersaldemo.mango.data.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SyncViewModel (application: Application): AndroidViewModel(application){

    val syncDao = AppDatabase.getDatabase(application).syncDao()
    fun insertUser(user: User)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao).insertUser(user)
    }
    fun insertUserRole(userRole: List<UserRole>)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao).insertUserRole(userRole)
    }
    fun insertCustomer(customerList: List<Customer>)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao).insertCustomer(customerList)
    }
    fun insertCustomerMapping(customerMapping: List<CustomerMapping>)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao).insertCustomerMapping(customerMapping)
    }
    fun insertLanguageList(languageList: List<LanguageList>)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao).insertLanguageList(languageList)
    }
}