package com.example.api.MainActivity

import androidx.lifecycle.ViewModel
import com.example.api.Screens
import com.github.terrakok.cicerone.Router


class MainActivityViewModel(
    private val router: Router
):ViewModel() {

    fun back(){
        router.navigateTo(Screens.getHomeFragment())
    }

    fun create() {
        router.navigateTo(Screens.getHomeFragment())
    }

    fun favorite() {
        router.navigateTo(Screens.getFavoriteFragment())
    }

    fun sort() {
        router.navigateTo(Screens.getSortFragment())
    }
}