package com.example.api

import androidx.room.Room
import com.example.api.HomeFragment.HomeViewModel
import com.example.api.MainActivity.MainActivityViewModel
import com.example.api.DBandprovider.DBprovider
import com.example.api.FavoriteFragment.FavoritesViewModel
import com.example.api.InfoFragment.InfoViewModel
import com.example.api.Retrofit.RetrofitInst
import com.example.api.Retrofit.RepositoryAPI
import com.example.api.SortFragment.SortViewModel
import com.example.api.detailFragment.DetailViewModel
import com.example.api.repository.RepositorySQLite

import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
    viewModel  {InfoViewModel(get(), get())}
    viewModel { HomeViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get(),get()) }
    viewModel { MainActivityViewModel(get(),get(),get()) }
    viewModel { SortViewModel(get()) }
    viewModel{DetailViewModel(get(),get())}
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            DBprovider::class.java,
            "database"
        ).build()
    }
    single { RetrofitInst }
    single { get<DBprovider>().PersonDao() }
    single { RepositorySQLite(get()) }
    single { RepositoryAPI() }
}

