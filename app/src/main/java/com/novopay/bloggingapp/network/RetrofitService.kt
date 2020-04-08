package com.novopay.bloggingapp.network

import com.novopay.bloggingapp.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object RetrofitService{
    fun get(): Api = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
}

interface Api {

    @GET("users")
    fun getUserList(): Call<List<User>>
}