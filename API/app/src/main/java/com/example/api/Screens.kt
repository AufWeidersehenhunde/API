package com.example.api


import com.example.api.FavoriteFragment.FavoritesFragment
import com.example.api.HomeFragment.HomeFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun getHomeFragment() = FragmentScreen { HomeFragment() }
    fun getFavoriteFragment() = FragmentScreen { FavoritesFragment() }
}