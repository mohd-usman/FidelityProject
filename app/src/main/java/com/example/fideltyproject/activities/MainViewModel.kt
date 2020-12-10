package com.example.fideltyproject.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fideltyproject.model.SearchAnimeResponse
import com.example.fideltyproject.repository.MainRepository
import kotlinx.coroutines.*
import java.lang.Exception


open class MainViewModel:ViewModel() {


    private val repository = MainRepository()


    private val _animeList = MutableLiveData<List<SearchAnimeResponse.SearchAnimeItem?>?>()
    private val _errorMessage = MutableLiveData<String>()

    private lateinit var job: Job

    val articleList: LiveData<List<SearchAnimeResponse.SearchAnimeItem?>?> = _animeList
    val errorMessage: LiveData<String> = _errorMessage

    init {
     makeApiCall()
    }
    private fun makeApiCall() {
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getAnime()
                withContext(Dispatchers.Main){
                    when (response.isSuccessful) {
                        true -> _animeList.value=response.body()?.data

                        else ->  response.errorBody()?.let { _errorMessage.value = it.toString() }

                    }
                }
            } catch (exception: Exception) {
                _errorMessage.postValue(exception.toString())
            }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}