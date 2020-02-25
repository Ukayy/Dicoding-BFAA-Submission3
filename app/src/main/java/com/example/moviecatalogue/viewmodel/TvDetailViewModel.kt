package com.example.moviecatalogue.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.TVShowDetail
import com.example.moviecatalogue.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TvDetailViewModel : ViewModel(){
    private var data = MutableLiveData<TVShowDetail>()

    private fun getData(id: String) {
        NetworkConfig().create().getDetailTV(id).enqueue(object : Callback<TVShowDetail> {
            override fun onFailure(call: Call<TVShowDetail>, t: Throwable?) {
                Log.e("Exception", t?.message)
                data.value = null
            }

            override fun onResponse(call: Call<TVShowDetail>, response: Response<TVShowDetail>) {
                if (response.isSuccessful) {
                    response.body().let {
                        data.value = it
                    }
                }
            }

        })
    }

    fun setData(id: String?): MutableLiveData<TVShowDetail> {
        if (id != null) {
            getData(id)
        }
        return data
    }
}