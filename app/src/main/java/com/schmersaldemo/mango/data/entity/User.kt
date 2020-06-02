package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
 ***/
@Entity(tableName = "tbl_user")
data class User(
    @PrimaryKey val user_id:Int,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "password") val password: String?,
    @ColumnInfo(name = "role_id") val role_id: Int?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "branch") val branch: String?,
    @ColumnInfo(name = "access_authorization") val access_authorization: String?

)

