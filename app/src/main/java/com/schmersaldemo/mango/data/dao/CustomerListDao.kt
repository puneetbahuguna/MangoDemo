package com.schmersaldemo.mango.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.schmersaldemo.mango.data.entity.Customer
/***
Author: Puneet Bahuguna
 ***/
@Dao
interface CustomerListDao{

    @Query("SELECT tbl_customer.customer_id,name,address,city,country,federal_state,assigned_projects,customer_info FROM tbl_customer INNER JOIN tbl_map_customer WHERE role_id=:roleid AND tbl_map_customer.customer_id=tbl_customer.customer_id")
    suspend fun getCustomerList(roleid:Int):List<Customer>

    @Query("SELECT role FROM tbl_role WHERE role_id=:roleid")
    suspend fun getUserRole(roleid: Int):String

    @Query("SELECT role_de FROM tbl_role WHERE role_id=:roleid")
    suspend fun getUserRolePR(roleid: Int):String
}