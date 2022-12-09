package com.example.api.InfoFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.DBandprovider.PersonDb
import com.example.api.Screens
import com.example.api.repository.RepositorySQLite
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


 class InfoViewModel (private val router: Router,
    private val repositorySQLite: RepositorySQLite
): ViewModel() {
    private val _listCharacters = MutableStateFlow<PersonDb?>(null)
     val listCharacters : MutableStateFlow<PersonDb?> = _listCharacters

    fun getInfoFragment(uuid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _listCharacters.value = repositorySQLite.getInfo(uuid)
        }
    }

     fun routeToDetail(image: String, uuid:Int){
         router.navigateTo(Screens.getDetailFragment(image, uuid))
     }

     fun back() {
         router.navigateTo(Screens.getHomeFragment())
     }
 }
