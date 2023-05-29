package com.example.charts.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charts.ui.home.api.ApiService
import com.example.charts.ui.home.api.RetrofitClient
import com.example.charts.ui.home.base.BaseViewModel
import com.example.charts.ui.home.model.BusinessDto
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : BaseViewModel() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getStats(start: String? = null, end: String? = null): LiveData<BusinessDto> {
        return getS(start, end)
    }

    private fun getS(start: String?, end: String?): MutableLiveData<BusinessDto> {
        val data = MutableLiveData<BusinessDto>()
        viewModelScope.launch {
            apiService.getBusinessStats(start, end).enqueue(object :
                Callback<BusinessDto> {
                override fun onResponse(call: Call<BusinessDto>, response: Response<BusinessDto>) {
                    Log.e("jopa", "onResponse: ${response.body()}")
                    data.postValue(response.body())
                }

                override fun onFailure(call: Call<BusinessDto>, t: Throwable) {
                    Log.wtf("alo", t.localizedMessage)
                }
            })
        }
        return data
    }
}