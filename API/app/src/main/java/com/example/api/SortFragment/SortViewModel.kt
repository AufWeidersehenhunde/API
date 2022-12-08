package com.example.api.SortFragment

import androidx.lifecycle.ViewModel
import com.example.api.Screens
import com.github.terrakok.cicerone.Router

class SortViewModel (
    private val router: Router
): ViewModel(){

    fun goBack(){
        //change navigate to to back to, some bug
        router.navigateTo(Screens.getHomeFragment())
    }
    fun routeToBundle(statusApi:String,genderApi:String,speciesApi:String) {
        router.navigateTo(Screens.getHomeSortFragment(statusApi,genderApi, speciesApi))
    }


}