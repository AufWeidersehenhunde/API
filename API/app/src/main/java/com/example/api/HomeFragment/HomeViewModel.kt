package com.example.api.HomeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.repository.RepositorySQLite
import com.example.api.DBandprovider.PersonDb
import com.example.api.Retrofit.RepositoryAPI
import com.example.api.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class HomeViewModel (
    private val router: Router,
    private val repositorySQLite: RepositorySQLite,
    private val repositoryAPI: RepositoryAPI
        ): ViewModel() {
    private val _listCharacters = MutableStateFlow<List<PersonDb>>(emptyList())


    fun getCharacters(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val characters = repositoryAPI.getCharacters(page)
            _listCharacters.value = characters.results
            repositorySQLite.insertAllData(characters.results)
        }

    }
    fun getItFavorite(uuid:String) {
        viewModelScope.launch(Dispatchers.IO) {
           repositorySQLite.putInFavorite(uuid)
        }
    }

    fun observeAllPersons() = repositorySQLite.getAllSomethingData()
    fun favorite() {
        router.navigateTo(Screens.getFavoriteFragment())
    }

    fun delPerson(model:PersonDb) {
        viewModelScope.launch {
            viewModelScope.launch {
                repositorySQLite.deletePerson(model)
            }
        }
    }

}
