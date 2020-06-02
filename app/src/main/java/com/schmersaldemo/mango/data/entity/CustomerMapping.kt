package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
 ***/
@Entity(tableName = "tbl_map_customer")
data class CustomerMapping(
    @PrimaryKey val map_id:Int,
    @ColumnInfo(name = "customer_id") val customer_id: Int?,
    @ColumnInfo(name = "role_id") val role_id: Int?
    )