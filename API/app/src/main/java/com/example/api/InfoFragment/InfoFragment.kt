package com.example.api.InfoFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.Recycler.MyAdapterForInfo
import com.example.api.databinding.FragmentInfoBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class InfoFragment : Fragment(R.layout.fragment_info) {
    private var adapterInfo: MyAdapterForInfo? = null
    private val viewBinding: FragmentInfoBinding by viewBinding()
    private val viewModelInfo: InfoViewModel by viewModel()


    companion object {
        private const val DATA = "UUID"
        fun getInstance(data: Int) = InfoFragment().apply {
            arguments = Bundle().apply {
                putInt(DATA, data)
            }
        }
    }

    private fun observeElement() {
        viewModelInfo._listCharacters.onEach {
            adapterInfo?.set(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeElement()
        val uuidForInfo = arguments?.getInt(DATA)
        adapterInfo = MyAdapterForInfo()
        with(viewBinding.recyclerViewInfo) {
            layoutManager = GridLayoutManager(
                context, 1
            )
            adapter = adapterInfo
        }

        if (uuidForInfo != null) {
            viewModelInfo.getInfoFragment(uuidForInfo.toInt())
        }
    }
}
//val pic = findViewById<ImageView>(R.id.imageViewInfo)
//fun saveSomePic() {
//    val bitmap = getImageOfView(pic)
//    if(bitmap!=null){
//        saveToStorage(bitmap)
//    }
//}
//
//fun saveToStorage(bitmap: Bitmap){
//    val imageName = "rick_and_morty${System.currentTimeMillis()}.jpg"
//    var fos: OutputStream? = null
//    if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q){
//        this.requireContext()?.also { resolver->
//            val contentValues = ContentValues().apply{
//                put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
//                put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg")
//                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
//            }
//            val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//            fos = imageUri?.let {
//                resolver.openOutputStream(it)
//            }
//        }
//    }
//    else{
//        val imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//        val image  = File(imageDirectory, imageName)
//        fos = FileOutputStream(image)
//    }
//    fos?.use {
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,it)
//    }
//}
//fun getImageOfView(pic: ImageView?): Bitmap? {
//    var image : Bitmap? = null
//    try {
//        image = Bitmap.createBitmap(pic!!.measuredWidth, pic.measuredHeight, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(image)
//        pic.draw(canvas)
//    } catch (e:Exception){
//        Log.d("lappar","not pic")}
//    return image
//}