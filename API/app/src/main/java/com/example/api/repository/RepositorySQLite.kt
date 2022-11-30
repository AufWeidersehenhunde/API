package com.example.api.repository


import com.example.api.daos.PersonDao
import com.example.api.DBandprovider.PersonDb


class RepositorySQLite(
    private val personDao: PersonDao
) {

    fun getAllSomethingData() = personDao.getAllSomethingData()

    fun getAllFavoriteData() = personDao.getAllFavoriteData()

    fun insertAllData(list: List<PersonDb>) = personDao.insertAllData(list)

    fun insertFavorite(list: List<PersonDb>) = personDao.insertFavorite(list)

    suspend fun putInFavorite(uuid: String) = personDao.putInFavorite(uuid)

    suspend fun deleteFavoritePerson(uuid: String) = personDao.deleteFavoritePerson(uuid)

    suspend fun deletePerson(it: PersonDb) = personDao.deletePerson(it)

}


