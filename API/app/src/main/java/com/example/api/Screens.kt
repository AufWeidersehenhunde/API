package com.example.api


import com.example.api.FavoriteFragment.FavoritesFragment
import com.example.api.HomeFragment.HomeFragment
import com.example.api.InfoFragment.InfoFragment
import com.example.api.SortFragment.SortFragment
import com.example.api.detailFragment.DetailFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun getInfoFragment(uuid: Int) = FragmentScreen { InfoFragment.getInstance(uuid) }
    fun getHomeSortFragment(statusApi: String,genderApi: String,speciesApi:String) = FragmentScreen { HomeFragment.getInstance(statusApi,genderApi,speciesApi) }
    fun getHomeFragment() = FragmentScreen { HomeFragment() }
    fun getFavoriteFragment() = FragmentScreen { FavoritesFragment() }
    fun getSortFragment() = FragmentScreen {SortFragment()}
    fun getDetailFragment(uuid: Int) = FragmentScreen { DetailFragment.getInstance(uuid) }
}