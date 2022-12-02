package com.example.api.InfoFragment

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

class InfoViewModel (
    private val router: Router,
    private val repositorySQLite: RepositorySQLite
): ViewModel(){
    val _listCharacters = MutableStateFlow<List<PersonDb>>(emptyList())

    fun getInfoFragment(uuid:Int) {
        viewModelScope.launch(Dispatchers.IO) {
           _listCharacters.value = listOf(repositorySQLite.getInfo(uuid))
        }
    }
    fun getPicUri(uri:String){
        viewModelScope.launch {
            repositorySQLite.getPicture(uri)
        }
    }


}