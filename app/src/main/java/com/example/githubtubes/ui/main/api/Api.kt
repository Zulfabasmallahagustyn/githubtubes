package com.example.githubtubes.ui.main.api


import com.example.githubtubes.ui.main.User
import com.example.githubtubes.ui.main.UserResponse
import com.example.githubtubes.ui.main.model.DetailUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {

    @GET ( "search/users" )
    @Headers( "Authorization:token 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")
    fun getSeacrhUsers(
        @Query ("q") query : String
    ): Call<UserResponse>

    @GET ( "users/{username}" )
    @Headers( "Authorization:token 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")
    fun  getUserDetail (
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers( "Authorization:token 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")
    fun  getFollowers (
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers( "Authorization:token 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")
    fun  getFollowing (
        @Path("username") username: String
    ): Call<ArrayList<User>>




}