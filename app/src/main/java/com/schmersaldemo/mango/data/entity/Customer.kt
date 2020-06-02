package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
 ***/
@Entity(tableName = "tbl_customer")
data class Customer(
    @PrimaryKey val customer_id:Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "federal_state") val federal_state: String?,
    @ColumnInfo(name = "assigned_projects") val assigned_projects: String?,
    @ColumnInfo(name = "customer_info") val customer_info: String?

)
