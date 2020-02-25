package com.example.moviecatalogue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShow(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val title: String? = null,
    @SerializedName("vote_average")
    val score: String? = null,
    @SerializedName("first_air_date")
    val date: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val poster: String? = null
):Parcelable