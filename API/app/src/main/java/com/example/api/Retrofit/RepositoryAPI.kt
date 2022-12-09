package com.example.api.Retrofit

import com.example.api.CharacterList
import com.example.api.Retrofit.RetrofitInst.api


class RepositoryAPI {
    suspend fun getCharacters(page: Int): CharacterList {
        val itemsList: CharacterList?
        itemsList = api.getCharacters(page).body() ?: CharacterList(listOf())
        return itemsList
    }
}

