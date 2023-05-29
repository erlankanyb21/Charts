package com.example.charts.ui.home.model

import com.google.gson.annotations.SerializedName

data class BusinessDto(
    @SerializedName("call_count")
    val callCount: List<CallCount> = listOf(),
    @SerializedName("click_count")
    val clickCount: List<ClickCount> = listOf(),
    @SerializedName("message_count")
    val messageCount: List<MessageCount> = listOf(),
    @SerializedName("start_date")
    val startDate: Int = 0,
    @SerializedName("user_id")
    val userId: Int = 0,
    @SerializedName("view_count")
    val viewCount: List<ViewCount> = listOf()
) {
    data class CallCount(
        @SerializedName("count")
        val count: Int = 0,
        @SerializedName("date")
        val date: String = ""
    )

    data class ClickCount(
        @SerializedName("count")
        val count: Int = 0,
        @SerializedName("date")
        val date: String = ""
    )

    data class MessageCount(
        @SerializedName("count")
        val count: Int = 0,
        @SerializedName("date")
        val date: String = ""
    )

    data class ViewCount(
        @SerializedName("count")
        val count: Int = 0,
        @SerializedName("date")
        val date: String = ""
    )
}