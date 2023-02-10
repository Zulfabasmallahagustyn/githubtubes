package com.example.githubtubes.ui.main.data.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteUserDao {
    @Insert
    suspend fun addToFavorite (favoriteUser: FavoriteUser)

    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUser() : LiveData<List<FavoriteUser>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_user WHERE favorite_user.id = :id)")
    suspend fun checkUser(id: IntArray?) : Boolean

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun removeFromFavorite(id: IntArray?) : Int

    @Query("SELECT * FROM favorite_user")
    fun findAll() : Cursor
}