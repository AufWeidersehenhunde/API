package com.example.api.DBandprovider

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.api.daos.DaoDao


@Database(entities = [PersonDb::class], version = 1)
abstract class DBprovider : RoomDatabase() {

    abstract fun somethingDao(): DaoDao

}