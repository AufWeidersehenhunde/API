package com.example.api

import androidx.room.Room
import com.example.api.HomeFragment.HomeViewModel
import com.example.api.MainActivity.MainActivityViewModel
import com.example.api.DBandprovider.DBprovider
import com.example.api.repository.Repository

import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }

    viewModel { HomeViewModel(get(),get() , get())}
//    viewModel {EditViewModel(get(),get())}
    viewModel{FavoritesViewModel(get(),get(),get())}
    viewModel { MainActivityViewModel(get()) }
//    viewModel { AddViewModel(get(),get()) }
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            DBprovider::class.java,
            "database"
        ).build()
    }
    single { RetrofitInst }
    single { get<DBprovider>().somethingDao() }
    single { Repository (get()) }
    single { RepositoryRam () }
}

