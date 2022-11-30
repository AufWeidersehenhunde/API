package com.example.api.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.api.DBandprovider.PersonDb


@Dao
interface PersonDao {

    @Query("SELECT * FROM persons")
    fun getAllSomethingData(): LiveData<List<PersonDb>>

    @Query("SELECT*FROM persons WHERE isFavorite = 1")
    fun getAllFavoriteData(): LiveData<List<PersonDb>>

    @Query("UPDATE persons SET isFavorite = 1 WHERE uuid =:uuid ")
    suspend fun putInFavorite(uuid: String)

    @Query("UPDATE persons SET isFavorite = 0 WHERE uuid =:uuid ")
    suspend fun deleteFavoritePerson(uuid: String)

    @Insert
    fun insertFavorite(list: List<PersonDb>)

    @Insert
    fun insertAllData(list: List<PersonDb>)

    @Delete
    suspend fun deletePerson(it: PersonDb)

}