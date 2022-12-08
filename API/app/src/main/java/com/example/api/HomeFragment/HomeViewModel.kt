package com.example.api.HomeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.repository.RepositorySQLite
import com.example.api.DBandprovider.PersonDb
import com.example.api.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class HomeViewModel (
    private val router: Router,
    private val repositorySQLite: RepositorySQLite
        ): ViewModel() {
    private val _listCharacters = MutableStateFlow<List<PersonDb>>(emptyList())
    val listCharacters :MutableStateFlow<List<PersonDb>> = _listCharacters

    fun searchAny(any:String){
        viewModelScope.launch {
            _listCharacters.value = repositorySQLite.putInSearch(any)
        }
    }

    fun viewSortPersons(statusApi:String,genderApi:String,speciesApi:String){
        viewModelScope.launch (Dispatchers.IO){
           _listCharacters.value = repositorySQLite.putInSort(statusApi,genderApi,speciesApi)
        }
    }
    fun putInFavorite(uuid:Int) {
        viewModelScope.launch(Dispatchers.IO) {
           repositorySQLite.putInFavorite(uuid)
        }
    }

    fun observeAllPersons()  {
        viewModelScope.launch {
            repositorySQLite.observeAllSomethingData().collect{
                _listCharacters.value = it
            }
        }
    }
    fun routeToInfo(uuid:Int){
        router.newRootScreen(Screens.getInfoFragment(uuid))
    }
    fun back() {
        router.newRootScreen(Screens.getHomeFragment())
    }

    fun delPerson(model:PersonDb) {
            viewModelScope.launch {
                repositorySQLite.deletePerson(model)
            }
        }

    fun toFavorite() {
        router.newRootScreen(Screens.getFavoriteFragment())
    }

    fun goToSorting() {
        router.newRootScreen(Screens.getSortFragment())
    }
}


