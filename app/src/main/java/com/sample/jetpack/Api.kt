package com.sample.jetpack

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("movie/in_theaters")
    fun getMovies(@Field("startPosition") startPosition: Int, @Field("PER_PAGE") PER_PAGE: Int): Call<Movies>
}
