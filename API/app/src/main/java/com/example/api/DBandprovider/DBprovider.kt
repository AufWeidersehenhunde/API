package com.example.architecturecomponent.dpandprovider

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.architecturecomponent.daos.DaoDao


@Database(entities = [SomethingDb::class], version = 1)
abstract class DBprovider : RoomDatabase() {

    abstract fun somethingDao(): DaoDao

}