package com.example.api.Retrofit

import com.example.api.CharacterList
import com.example.api.DBandprovider.PersonDb
import com.example.api.Retrofit.RetrofitInst.api


class RepositoryAPI(
) {
    val list = mutableListOf<PersonDb>()

    suspend fun getCharacters(page: Int): CharacterList {
        val itemsList: CharacterList?
        itemsList = api.getCharacters(page).body() ?: CharacterList(listOf())
        return itemsList
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
