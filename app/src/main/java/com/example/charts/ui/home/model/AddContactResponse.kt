package com.example.charts.ui.home.model


import com.google.gson.annotations.SerializedName


data class AddContactResponse(
    @SerializedName("business")
    val business: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("type")
    val type: String = "",
    @SerializedName("value")
    val value: String = ""
)
