package com.schmersaldemo.mango.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.schmersaldemo.mango.data.entity.User
/***
Author: Puneet Bahuguna
 ***/
@Dao
interface UserDao{

    @Query("SELECT username FROM tbl_user")
    suspend fun getAllLoggedInUSer():List<String>

    /*@Insert
    fun insertUser(vararg user:User)*/

    @Query("SELECT * FROM tbl_user WHERE username LIKE :username AND " + "password LIKE :password")
    suspend fun authenticateUser(username:String,password:String):User

    @Query("SELECT language_code FROM tbl_supported_languages")
    suspend fun getLanguageCodes():List<String>

}