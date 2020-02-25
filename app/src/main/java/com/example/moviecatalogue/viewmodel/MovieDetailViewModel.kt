package com.example.moviecatalogue.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.MovieDetail
import com.example.moviecatalogue.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieDetailViewModel : ViewModel() {
    private var data = MutableLiveData<MovieDetail>()

    private fun getData(id: String) {
        NetworkConfig().create().getDetailMovie(id).enqueue(object : Callback<MovieDetail> {
            override fun onFailure(call: Call<MovieDetail>, t: Throwable?) {
                Log.e("Exception", t?.message)
                data.value = null
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                if (response.isSuccessful) {
                    response.body().let {
                        data.value = it
                    }
                }
            }

        })
    }

    fun setData(id: String?): MutableLiveData<MovieDetail> {
        if (id != null) {
            getData(id)
        }
        return data
    }
}