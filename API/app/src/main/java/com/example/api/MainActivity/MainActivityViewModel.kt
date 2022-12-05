package com.example.api.MainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.Retrofit.RepositoryAPI
import com.example.api.Screens
import com.example.api.repository.RepositorySQLite
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel(
    private val router: Router,
    private val repositorySQLite: RepositorySQLite,
    private val repositoryAPI: RepositoryAPI
) : ViewModel() {

    fun getCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val characters = repositoryAPI.getCharacters(page)
            repositorySQLite.insertAllData(characters.results)
        }
    }

    fun back() {
        router.navigateTo(Screens.getHomeFragment())
    }

    fun create() {
        router.navigateTo(Screens.getHomeFragment())
    }

    fun favorite() {
        router.navigateTo(Screens.getFavoriteFragment())
    }

    fun sort() {
        router.navigateTo(Screens.getSortFragment())
    }
}