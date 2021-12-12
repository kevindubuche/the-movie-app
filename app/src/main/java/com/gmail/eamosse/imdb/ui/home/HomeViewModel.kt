package com.gmail.eamosse.imdb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.MovieOfACategory
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    private val _discovers: MutableLiveData<List<MovieOfACategory>> = MutableLiveData()
    val discoveries: LiveData<List<MovieOfACategory>>
        get() = _discovers


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
                    _discovers.postValue(result.data)
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
                    val dis = _discovers.value;
                    dis.let {
                        val movies = listOf<MovieOfACategory>(*it!!.toTypedArray(), *result.data.toTypedArray());
                        _discovers.postValue(movies)
                    }
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }


}