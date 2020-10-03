package com.sousnein.lethaljokes

import android.graphics.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET(".")
    fun getJoke() : Call<String>

    companion object {
        fun create(apiUrl:String) : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(apiUrl)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}