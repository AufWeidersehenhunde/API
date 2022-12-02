package com.example.api.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.api.DBandprovider.PersonDb
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {

    @Query("SELECT * FROM persons")
    fun getAllSomethingData(): Flow<List<PersonDb>>

    @Query("SELECT * FROM persons WHERE isFavorite = 1")
    fun getAllFavoriteData(): LiveData<List<PersonDb>>

    @Query("UPDATE persons SET isFavorite = 1 WHERE id =:uuid ")
    suspend fun putInFavorite(uuid: Int)

    @Query("SELECT * FROM persons WHERE id =:uuid")
    suspend fun getItemForId(uuid: Int): PersonDb

    @Query("UPDATE persons SET isFavorite = 0 WHERE id =:uuid ")
    suspend fun deleteFavoritePerson(uuid: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(list: List<PersonDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllData(list: List<PersonDb>)

    @Delete
    suspend fun deletePerson(it: PersonDb)

    @Query("SELECT*FROM persons WHERE status = :statusApi and gender = :genderApi and species = :speciesApi")
    suspend fun putInSort(statusApi: String, genderApi: String, speciesApi: String) : List<PersonDb>

    @Query("SELECT*FROM persons WHERE image =:uri")
    fun getPicture(uri: String):PersonDb
}