package com.example.api.FavoriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.DBandprovider.PersonDb
import com.example.api.Screens
import com.example.api.repository.RepositorySQLite
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val router: Router,
    private val repositorySQL: RepositorySQLite
) : ViewModel() {
    private val _listFavorite = MutableStateFlow<List<PersonDb>>(emptyList())
    val listFavorite: MutableStateFlow<List<PersonDb>> = _listFavorite


    init {
        getCharactersFavorite()
    }

    private fun getCharactersFavorite() {
        viewModelScope.launch {
            repositorySQL.getAllFavoriteData().collect{
                _listFavorite.value = it
            }
        }
    }

    fun delFavoritePerson(uuid: Int) {
        viewModelScope.launch {
            repositorySQL.deleteFavoritePerson(uuid)
        }
    }

    fun back() {
        //change navigate to to back to, some bug
        router.newRootScreen(Screens.getHomeFragment())
    }

}