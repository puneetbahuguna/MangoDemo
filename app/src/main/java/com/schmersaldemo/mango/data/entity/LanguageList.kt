package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
 ***/
@Entity(tableName = "tbl_supported_languages")
data class LanguageList(
    @PrimaryKey val language_id:Int,
    @ColumnInfo(name = "language_code") val language_code: String?,
    @ColumnInfo(name = "language_name") val language_name: String?
)