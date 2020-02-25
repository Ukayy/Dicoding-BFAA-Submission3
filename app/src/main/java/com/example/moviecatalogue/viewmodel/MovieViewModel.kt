package com.example.moviecatalogue.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.MovieResponse
import com.example.moviecatalogue.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieViewModel : ViewModel() {

    private var data = MutableLiveData<MovieResponse>()

    init {
        getMovie()
    }

    private fun getMovie() {
        NetworkConfig().create().getDiscoverMovie().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("Exception", t.message)
                data.value = null
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body().let {
                        data.value = it
                    }
                }
            }

        })
    }

    fun setData(): MutableLiveData<MovieResponse> {
        return data
    }
}