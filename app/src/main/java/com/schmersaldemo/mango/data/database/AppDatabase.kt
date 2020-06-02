package com.schmersaldemo.mango.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.schmersaldemo.mango.data.dao.CustomerListDao
import com.schmersaldemo.mango.data.dao.SyncDao
import com.schmersaldemo.mango.data.dao.UserDao
import com.schmersaldemo.mango.data.entity.*
/***
Author: Puneet Bahuguna
 ***/
@Database(entities = arrayOf(User::class,Customer::class,UserRole::class,CustomerMapping::class,LanguageList::class),version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun customerDao(): CustomerListDao
    abstract fun syncDao():SyncDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "schmersal"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}