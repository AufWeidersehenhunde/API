package com.example.api.repository


import com.example.api.daos.PersonDao
import com.example.api.DBandprovider.PersonDb


class RepositorySQLite(
    private val personDao: PersonDao
) {

    fun getAllSomethingData() = personDao.getAllSomethingData()

    fun getAllFavoriteData() = personDao.getAllFavoriteData()

    fun insertAllData(list: List<PersonDb>) = personDao.insertAllData(list)

    suspend fun getInfo(uuid: Int) = personDao.getItemForId(uuid)

    suspend fun putInFavorite(uuid: Int) = personDao.putInFavorite(uuid)

    suspend fun deleteFavoritePerson(uuid: Int) = personDao.deleteFavoritePerson(uuid)

    suspend fun deletePerson(it: PersonDb) = personDao.deletePerson(it)

    suspend fun putInSort(statusApi: String, genderApi: String, speciesApi: String) = personDao.putInSort(statusApi, genderApi, speciesApi)

    suspend fun putInSearch(any: String): List<PersonDb>  = personDao.putInSearch(any)

}


