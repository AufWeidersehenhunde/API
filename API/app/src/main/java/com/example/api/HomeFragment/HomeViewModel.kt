package com.example.api.HomeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
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
    private val repositorySQLite: RepositorySQLite
        ): ViewModel() {
     val _listCharacters = MutableStateFlow<List<PersonDb>>(emptyList())


    fun viewSortPersons(statusApi:String,genderApi:String,speciesApi:String){
        viewModelScope.launch (Dispatchers.IO){
           _listCharacters.value = repositorySQLite.putInSort(statusApi,genderApi,speciesApi)
        }
    }
    fun getItFavorite(uuid:Int) {
        viewModelScope.launch(Dispatchers.IO) {
           repositorySQLite.putInFavorite(uuid)
        }
    }

    fun observeAllPersons()  {
        viewModelScope.launch {
            repositorySQLite.getAllSomethingData().collect{
                _listCharacters.value = it
            }
        }
    }
    fun routeToInfo(uuid:Int){
        router.navigateTo(Screens.getInfoFragment(uuid))
    }


    fun delPerson(model:PersonDb) {
            viewModelScope.launch {
                repositorySQLite.deletePerson(model)
            }
        }
    }


