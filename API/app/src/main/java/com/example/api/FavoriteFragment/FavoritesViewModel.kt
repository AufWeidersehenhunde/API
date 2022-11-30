package com.example.api.FavoriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.Retrofit.RepositoryAPI
import com.example.api.Screens
import com.example.api.repository.RepositorySQLite
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val router: Router,
    private val repositorySQL: RepositorySQLite,
    private val repositoryAPI: RepositoryAPI
) : ViewModel() {

    fun getCharactersFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            repositorySQL.insertFavorite(repositoryAPI.list)
        }
    }

    fun observeAllFavoriteData() = repositorySQL.getAllFavoriteData()
    fun backFragment() {
        router.backTo(Screens.getHomeFragment())
    }

    fun delFavoritePerson(uuid: String) {
        viewModelScope.launch {
            repositorySQL.deleteFavoritePerson(uuid)
        }
    }
}