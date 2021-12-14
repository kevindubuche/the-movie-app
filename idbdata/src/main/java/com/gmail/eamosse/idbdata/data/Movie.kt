package com.gmail.eamosse.idbdata.data

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val vote_count: String,
    val release_date: String,
//    val production_companies: List<Company>,
    val original_language: String
)
