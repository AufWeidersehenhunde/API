package com.example.api.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.api.DBandprovider.PersonDb



@Dao
interface DaoDao {

    @Query("SELECT * FROM rick_and_morty1")
    fun getAllSomethingData(): LiveData<List<PersonDb>>

    @Query("SELECT * FROM rick_and_morty1 WHERE uuid =:uuid")
    suspend fun getItemForId(uuid: String): PersonDb


    @Insert
    fun insertAllData(list: List<PersonDb>)

    @Insert
    suspend fun addSomething(model: PersonDb)

    @Delete
    suspend fun deleteSomething(model: PersonDb)

    @Update
    suspend fun updateSomething(model: PersonDb)
}