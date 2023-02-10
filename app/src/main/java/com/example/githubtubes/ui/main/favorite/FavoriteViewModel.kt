package com.example.githubtubes.ui.main.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubtubes.ui.main.data.local.FavoriteUser
import com.example.githubtubes.ui.main.data.local.FavoriteUserDao
import com.example.githubtubes.ui.main.data.local.UserDataBase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var userDao : FavoriteUserDao?
    private var userDb : UserDataBase?

    init {
        userDb = UserDataBase.getDataBase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser () : LiveData<List<FavoriteUser>>?{
        return  userDao?.getFavoriteUser()
    }
}