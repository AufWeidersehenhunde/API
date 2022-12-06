package com.example.api.FavoriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.DBandprovider.PersonDb
import com.example.api.Retrofit.RepositoryAPI
import com.example.api.Screens
import com.example.api.repository.RepositorySQLite
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val router: Router,
    private val repositorySQL: RepositorySQLite
) : ViewModel() {
    val listFavorite = MutableStateFlow<List<PersonDb>>(emptyList())


    init {
        getCharactersFavorite()
    }

    private fun getCharactersFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            listFavorite.value = repositorySQL.getAllFavoriteData()
        }
    }

    fun delFavoritePerson(uuid: Int) {
        viewModelScope.launch {
            repositorySQL.deleteFavoritePerson(uuid)
        }
    }

    fun back() {
        //change navigate to to back to, some bug
        router.navigateTo(Screens.getHomeFragment())
    }
}