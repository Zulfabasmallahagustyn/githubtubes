package com.example.githubtubes.ui.main.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class UserDataBase : RoomDatabase() {
    companion object {
        var INSTANCE : UserDataBase? = null

        fun getDataBase(context: Context) : UserDataBase? {
            if (INSTANCE==null){
                synchronized(UserDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, UserDataBase::class.java, "user_database").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun favoriteUserDao() : FavoriteUserDao
}