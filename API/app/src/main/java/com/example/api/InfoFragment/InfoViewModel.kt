package com.example.api.InfoFragment

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore.Images.Media.insertImage
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.DBandprovider.PersonDb
import com.example.api.R
import com.example.api.Retrofit.RepositoryAPI
import com.example.api.Screens
import com.example.api.repository.RepositorySQLite
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.scope.emptyState
import java.io.File

 class InfoViewModel (private val router: Router,
    private val repositorySQLite: RepositorySQLite
): ViewModel() {
    private val _listCharacters = MutableStateFlow<PersonDb?>(null)
     val listCharacters : MutableStateFlow<PersonDb?> = _listCharacters
     val listInfo = MutableStateFlow<List<PersonDb>>(emptyList())

    fun getInfoFragment(uuid: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _listCharacters.value = repositorySQLite.getInfo(uuid)
        }
    }
     fun takePersonsImage(){
         viewModelScope.launch {
             repositorySQLite.getAllSomethingData().collect{
                 listInfo.value = it
             }
         }
     }
     fun routeToDetail(image: String, uuid:Int){
         router.navigateTo(Screens.getDetailFragment(image, uuid))
     }

     fun back() {
         router.navigateTo(Screens.getHomeFragment())
     }
 }
