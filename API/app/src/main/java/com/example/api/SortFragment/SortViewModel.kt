package com.example.api.SortFragment

import androidx.lifecycle.ViewModel
import com.example.api.Screens
import com.github.terrakok.cicerone.Router

class SortViewModel (
    private val router: Router
): ViewModel(){

    fun goBack(){
        router.newRootScreen(Screens.getHomeFragment())
    }
    fun routeToBundle(statusApi:String,genderApi:String,speciesApi:String) {
        router.newRootScreen(Screens.getHomeSortFragment(statusApi,genderApi, speciesApi))
    }


}