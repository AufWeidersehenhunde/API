package com.example.architecturecomponent.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.architecturecomponent.dpandprovider.SomethingDb


@Dao
interface DaoDao {

    @Query("SELECT * FROM txt")
    fun getAllSomethingData():LiveData<List<SomethingDb>>

    @Query("SELECT * FROM txt WHERE uuid =:uuid")
    suspend fun getItemForId(uuid: String): SomethingDb

    @Insert
    suspend fun addSomething(model: SomethingDb)

    @Delete
    suspend fun deleteSomething(model: SomethingDb)

    @Update
    suspend fun updateSomething(model: SomethingDb)
}