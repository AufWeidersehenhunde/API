package com.example.api.detailFragment

import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.DBandprovider.PersonDb
import com.example.api.Screens
import com.example.api.repository.RepositorySQLite
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel (private val router: Router,
                       private val repositorySQLite: RepositorySQLite
): ViewModel() {
    private val _listCharacters = MutableStateFlow<PersonDb?>(null)
    val listCharacters : MutableStateFlow<PersonDb?> = _listCharacters

    fun saveImage(context: Context, image: Bitmap){
        MediaStore.Images.Media.insertImage(context.contentResolver, image, "name", "name")
    }

    fun getDetailFragment(uuid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _listCharacters.value = repositorySQLite.getInfo(uuid)
        }
    }

    fun back() {
        router.newRootScreen(Screens.getHomeFragment())
    }

}