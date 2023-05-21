package com.example.charts.ui.home.model

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("callCount"),
        TODO("clickCount"),
        TODO("messageCount"),
        parcel.readInt(),
        parcel.readInt(),
        TODO("viewCount")
    ) {
    }

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(startDate)
        parcel.writeInt(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BusinessDto> {
        override fun createFromParcel(parcel: Parcel): BusinessDto {
            return BusinessDto(parcel)
        }

        override fun newArray(size: Int): Array<BusinessDto?> {
            return arrayOfNulls(size)
        }
    }
}