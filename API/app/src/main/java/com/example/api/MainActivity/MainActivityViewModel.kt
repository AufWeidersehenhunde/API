package com.example.api.MainActivity

import androidx.lifecycle.ViewModel
import com.example.api.Screens
import com.github.terrakok.cicerone.Router

class MainActivityViewModel(
    private val router: Router
):ViewModel() {

    fun create() {
        router.navigateTo(Screens.getHomeFragment())
    }
}