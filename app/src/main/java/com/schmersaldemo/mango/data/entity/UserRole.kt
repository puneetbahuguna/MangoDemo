package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
 ***/
@Entity(tableName = "tbl_role")
data class UserRole(
    @PrimaryKey val role_id:Int,
    @ColumnInfo(name = "role") val role: String?,
    @ColumnInfo(name = "role_de") val role_de: String?
    )