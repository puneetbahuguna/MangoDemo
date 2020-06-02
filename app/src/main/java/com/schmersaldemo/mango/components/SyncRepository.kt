package com.schmersaldemo.mango.components

import com.schmersaldemo.mango.data.dao.SyncDao
import com.schmersaldemo.mango.data.entity.*
/***
Author: Puneet Bahuguna
 ***/
class SyncRepository(private val syncDao: SyncDao){

    fun insertCustomer(customerList:List<Customer>){
        syncDao.insertCustomer(customerList)
    }
    fun insertUser(user: User){
        syncDao.insertUser(user)
    }
    fun insertCustomerMapping(customerMapping:List<CustomerMapping>){
        syncDao.insertCustomerMapping(customerMapping)
    }
    fun insertUserRole(userRole:List<UserRole>){
        syncDao.insertUserRole(userRole)
    }
    fun insertLanguageList(languageList:List<LanguageList>){
        syncDao.insertLanguageList(languageList)
    }
}