package com.example.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.HomeFragment.HomeViewModel
import com.example.architecturecomponent.repository.Repository
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val router: Router,
    private val repo: Repository,
    private val repo2: RepositoryRam
) : ViewModel() {
    var listCharactersFavorite = MutableLiveData<List<Character1>>()


    fun getCharactersFavorite() {
        viewModelScope.launch {
            listCharactersFavorite.value = repo2.list
        }
    }
    fun backFragment() {
        router.backTo(Screens.getHomeFragment())
    }

    fun delSomething(it: Character1) {
        viewModelScope.launch {
            val list = listCharactersFavorite.value?.toMutableList()
            list?.remove(it)
            listCharactersFavorite.value = list!!
        }
    }
}