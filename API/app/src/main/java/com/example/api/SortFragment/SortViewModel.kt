package com.example.api.SortFragment

import androidx.lifecycle.ViewModel
import com.example.api.Retrofit.RepositoryAPI
import com.example.api.Screens
import com.example.api.repository.RepositorySQLite
import com.github.terrakok.cicerone.Router

class SortViewModel (
    private val router: Router,
    private val repositorySQLite: RepositorySQLite,
    private val repositoryAPI: RepositoryAPI
): ViewModel(){

    fun goBack(){
        router.backTo(Screens.getHomeFragment())
    }
    fun routeToBundle(statusApi:String,genderApi:String,speciesApi:String) {
        router.navigateTo(Screens.getHomeSortFragment(statusApi,genderApi, speciesApi))
    }


}