package com.example.charts.ui.home.api

import com.example.charts.ui.home.model.BusinessDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("business/v1/stats/all/")
    fun getBusinessStats(
        @Query("start") start: String? = null,
        @Query("end") end: String? = null
    ): Call<BusinessDto>
}