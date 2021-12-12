package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.MovieOfACategory
import com.google.gson.annotations.SerializedName

internal data class MovieOfACategoryResponse(
    @SerializedName("results")
    val results: List<MovieOfACategoryItem>
) {
    data class MovieOfACategoryItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String ?,
        @SerializedName("overview")
        val overview: String ?,
        @SerializedName("poster_path")
        val poster_path: String ?,
        @SerializedName("adult")
        val mature: Boolean ?
    )
}

internal fun MovieOfACategoryResponse.MovieOfACategoryItem.toMovieOfACategory() = MovieOfACategory(
    id = id,
    title = title ?: "",
    overview = overview ?: "",
    poster_path = poster_path ?: ""
)
