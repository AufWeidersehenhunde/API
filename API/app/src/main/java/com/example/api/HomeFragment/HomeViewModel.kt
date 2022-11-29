package com.example.api.HomeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.repository.Repository

import com.example.api.DBandprovider.PersonDb
import com.example.api.RepositoryRam
import com.example.api.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel (
    private val router: Router,
    private val repo: Repository,
    private val repo2:RepositoryRam
        ): ViewModel() {
    var listCharacters = MutableLiveData<List<PersonDb>>()


    fun getCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val characters = repo2.getCharacters(page)
            listCharacters.postValue(characters.results)
            repo.insertAllData(characters.results)
        }

    }

    fun observeAllSomething() = repo.getAllSomethingData()
    fun favorite() {
        router.navigateTo(Screens.getFavoriteFragment())
    }
    fun delSomething(it:PersonDb){
        viewModelScope.launch {
            val list = listCharacters.value?.toMutableList()
            list?.remove(it)
            listCharacters.value = list!!
        }
    }

    fun takeThis(it:PersonDb){
        viewModelScope.launch {
            repo2.addFavorite(it)
        }
    }
}
