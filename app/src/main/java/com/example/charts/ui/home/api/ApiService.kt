package com.example.charts.ui.home.api

import com.example.charts.ui.home.model.AddContactDto
import com.example.charts.ui.home.model.AddContactResponse
import com.example.charts.ui.home.model.BusinessDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("business/v1/stats/all/")
    fun getBusinessStats(
        @Query("start") start: String? = null, @Query("end") end: String? = null
    ): Call<BusinessDto>

    /*----------------------------------------*/

    @POST("business/v1/profile/contacts/")
    fun postContacts(
        @Body addContactDto: List<AddContactDto>
    ): Call<List<AddContactResponse>>

    @PUT("business/v1/profile/contacts/{id}")
    fun updateContacts(
        @Path ("id") id: Int,
        @Body addContactDto: List<AddContactDto>,
    ): Call<AddContactResponse>

    @PATCH("business/v1/profile/contacts/{id}")
    fun changeContacts(
        @Path ("id") id: Int,
        @Body addContactDto: List<AddContactDto>,
    ): Call<AddContactResponse>
}