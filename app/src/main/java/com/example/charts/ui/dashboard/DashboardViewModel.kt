package com.example.charts.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.charts.ui.home.api.ApiService
import com.example.charts.ui.home.api.RetrofitClient
import com.example.charts.ui.home.base.BaseViewModel
import com.example.charts.ui.home.model.AddContactDto
import com.example.charts.ui.home.model.AddContactResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getContacts(addContactDto: List<AddContactDto>): LiveData<List<AddContactResponse>> {
        return getContact(addContactDto)
    }

    private fun getContact(addContactDto: List<AddContactDto>): MutableLiveData<List<AddContactResponse>> {
        val data = MutableLiveData<List<AddContactResponse>>()
        viewModelScope.launch {
            apiService.postContacts(addContactDto).enqueue(object :
                Callback<List<AddContactResponse>> {
                override fun onResponse(
                    call: Call<List<AddContactResponse>>,
                    response: Response<List<AddContactResponse>>
                ) {
                    data.postValue(response.body())
                    Log.e("S", "onResponse: ${response.body()}" )
                }

                override fun onFailure(call: Call<List<AddContactResponse>>, t: Throwable) {
                    Log.wtf("jopa", "onFailure: ${t.localizedMessage}" )
                }

            })
        }
        return data
    }
}