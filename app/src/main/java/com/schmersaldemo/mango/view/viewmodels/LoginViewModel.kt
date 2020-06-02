package com.schmersaldemo.mango.view.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.schmersaldemo.mango.components.LoginRepository
import com.schmersaldemo.mango.data.database.AppDatabase
import com.schmersaldemo.mango.data.entity.User


class LoginViewModel(application: Application):AndroidViewModel(application){
    val loginDao = AppDatabase.getDatabase(application).userDao()
    suspend fun getAllLoginUsers():List<String> {
          return LoginRepository(loginDao).getLoggedInUsers()
    }

    suspend fun authenticateLocaluser(username:String,password:String):User{
        return LoginRepository(loginDao).authenticateLocalUser(username,password)
    }

    suspend fun getLanguageCodes():List<String>{
        return LoginRepository(loginDao).getLanguageCodes()
    }
     /*fun insertUser(user:User)=viewModelScope.launch(Dispatchers.IO) {
        LoginRepository(loginDao).insertUser(user)
    }*/
}