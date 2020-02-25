package com.example.moviecatalogue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("original_title")
    val title: String? = null,
    @SerializedName("vote_average")
    val score: String? = null,
    @SerializedName("release_date")
    val date: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val poster: String? = null
) : Parcelable