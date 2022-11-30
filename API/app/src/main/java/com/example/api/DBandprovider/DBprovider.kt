package com.example.api.DBandprovider

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.api.daos.PersonDao


@Database(entities = [PersonDb::class], version = 1)
abstract class DBprovider : RoomDatabase() {

    abstract fun PersonDao(): PersonDao
}