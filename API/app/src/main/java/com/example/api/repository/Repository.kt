package com.example.api.repository



import com.example.api.daos.DaoDao
import com.example.api.DBandprovider.PersonDb
import kotlinx.coroutines.withContext


class Repository(
    private val somethingDao: DaoDao
) {


    fun getAllSomethingData() = somethingDao.getAllSomethingData()
    fun insertAllData(list:List<PersonDb>) = somethingDao.insertAllData(list)

    suspend fun getItemForId(uuid: String) = somethingDao.getItemForId(uuid)

    suspend fun addSomething(model: PersonDb) = somethingDao.addSomething(model)

    suspend fun deleteSomething(model: PersonDb) = somethingDao.deleteSomething(model)

    suspend fun updateSomething(model: PersonDb) = somethingDao.updateSomething(model)


}