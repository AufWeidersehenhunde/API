package com.example.architecturecomponent.repository


import com.example.architecturecomponent.daos.DaoDao
import com.example.architecturecomponent.dpandprovider.DBprovider
import com.example.architecturecomponent.dpandprovider.SomethingDb
import com.github.terrakok.cicerone.Router
import java.util.UUID

class Repository(
    private val somethingDao: DaoDao
) {

    fun getAllSomethingData() = somethingDao.getAllSomethingData()

    suspend fun getItemForId(uuid: String) = somethingDao.getItemForId(uuid)

    suspend fun addSomething(model: SomethingDb) = somethingDao.addSomething(model)

    suspend fun deleteSomething(model: SomethingDb) = somethingDao.deleteSomething(model)

    suspend fun updateSomething(model: SomethingDb) = somethingDao.updateSomething(model)


}