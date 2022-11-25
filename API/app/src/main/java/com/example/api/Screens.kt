package com.example.api


import com.example.api.HomeFragment.HomeFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
//    fun getEditTextFragment(uuid: String) = FragmentScreen { EditFragment.getInstance(uuid) }
    fun getHomeFragment() =  FragmentScreen { HomeFragment() }
   fun getFavoriteFragment() =  FragmentScreen { FavoritesFragment() }
}