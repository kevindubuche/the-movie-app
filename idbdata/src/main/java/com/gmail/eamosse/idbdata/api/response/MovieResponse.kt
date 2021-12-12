package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.google.gson.annotations.SerializedName


internal data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("release_date")
    val release_date: String
)


internal fun MovieResponse.toMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    poster_path = poster_path,
    vote_count = vote_count,
    release_date = release_date
)