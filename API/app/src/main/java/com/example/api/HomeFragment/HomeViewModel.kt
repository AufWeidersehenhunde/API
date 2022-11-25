package com.example.api.HomeFragment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.Character1
import com.example.api.FavoritesViewModel
import com.example.api.RepositoryRam
import com.example.api.Screens
import com.example.architecturecomponent.repository.Repository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch


class HomeViewModel (
    private val router: Router,
    private val repo: Repository,
    private val repo2:RepositoryRam
        ): ViewModel() {
    var listCharacters = MutableLiveData<List<Character1>>()
    var listCall = MutableLiveData<List<Character1>?>()

    fun getCharacters(page: Int) {
        viewModelScope.launch {
            val characters = repo2.getCharacters(page)
            listCharacters.value = characters.results
        }
    }
    fun favorite() {
        router.navigateTo(Screens.getFavoriteFragment())
    }
    fun delSomething(it:Character1){
        viewModelScope.launch {
            val list = listCharacters.value?.toMutableList()
            list?.remove(it)
            listCharacters.value = list!!
        }
    }

    fun takeThis(it:Character1){
        viewModelScope.launch {
            repo2.addFavorite(it)
        }
    }
}
