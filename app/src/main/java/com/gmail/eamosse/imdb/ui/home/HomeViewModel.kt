package com.gmail.eamosse.imdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _moc: MutableLiveData<List<MovieOfACategory>> = MutableLiveData()
    val moc: LiveData<List<MovieOfACategory>>
        get() = _moc

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie>
        get() = _movie

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getToken()) {
                is Result.Succes -> {
                    _token.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Succes -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getMoc(id: Int, pagination:Int = 1)  {//get Movies of a category
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovieOfACategory(id, pagination)) {
                is Result.Succes -> {
                    _moc.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun loadMoreData(id: Int, page:Int = 1)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovieOfACategory(id, page)) {
                is Result.Succes -> {
                    _moc.value.let {
                        val movies = listOf<MovieOfACategory>(*it!!.toTypedArray(), *result.data.toTypedArray());
                        _moc.postValue(movies)
                    }
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getMovie(id: Int)  {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovie(id)) {
                is Result.Succes -> {
                    _movie.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getSimilarmovies(id: Int, pagination:Int = 1)  {//get Movies of a category
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSimilarMovies(id, pagination)) {
                is Result.Succes -> {
                    _moc.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getPopularMovies()) {
                is Result.Succes -> {
                    _moc.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

}