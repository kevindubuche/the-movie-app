package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieOfACategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    // to get the movies of a specific category
    @GET("discover/movie")
    suspend fun getMovieOfACategory(@Query("with_genres") withGenres:Int, @Query("page") page:Int = 1): Response<MovieOfACategoryResponse>

    @GET("/3/movie/{movie}")
    suspend fun getMovie(@Path("movie") movie:Int): Response<MovieResponse>

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getSimilarMovies(@Path("movie_id")id: Int ,@Query("page") page: Int): Response<MovieOfACategoryResponse>

}