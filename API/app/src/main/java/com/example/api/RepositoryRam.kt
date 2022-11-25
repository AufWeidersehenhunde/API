package com.example.api

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.example.api.RetrofitInst.api
import com.github.terrakok.cicerone.Router
import retrofit2.Response


class RepositoryRam(
//   private val  apiRaM: ApiRaM
) {
    var list = mutableListOf<Character1>()



    suspend fun getCharacters(page: Int): CharacterList {
        val itemsList: CharacterList?
        itemsList = api.getCharacters(page).body() ?: CharacterList(listOf())
        return itemsList
    }

    fun addFavorite(it: Character1) {
        list.add(it)
    }
}
//       return safeApiCall { apiRaM.getCharacters(page) }
//    }
//
//    private inline fun <T> safeApiCall(apiCall: () -> Response<T>):ResponseExcep<T>{
//        return try {
//            ResponseExcep.success(apiCall.invoke())
//        }
//        catch (e:Exception){
//            ResponseExcep.failed(e)
//        }
//    }
//}
