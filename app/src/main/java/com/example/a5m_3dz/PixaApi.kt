package com.example.a5m_3dz

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getImages(
        @Query("key") key: String = "33921092-684893a004d9ac755f773f938",
        @Query("q") pictureWord: String,
        @Query("per_page") perPage : Int = 3,
        @Query("page") page : Int
    ): Call<PixaModel>

}