package com.example.api.detailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.api.DBandprovider.PersonDb
import com.example.api.R
import com.example.api.databinding.FragmentDetailBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewBinding: FragmentDetailBinding by viewBinding()
    private val viewModelDetail: DetailViewModel by viewModel()

    companion object {
        private const val UUID = "UUID"
        private const val IMAGE = "IMAGE"
        fun getInstance(image: String, uuid: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(UUID, uuid)
                putString(IMAGE, image)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uuidForDetail = arguments?.getInt(UUID)
        val image = arguments?.getString(IMAGE)
        if (uuidForDetail != null) {
            viewModelDetail.getDetailFragment(uuidForDetail.toInt())
        }
        fun bind(character: PersonDb) {
            viewBinding.apply {
                if (image == null) {
                    Glide.with(imageViewDetail.context)
                        .load(R.drawable.ic_baseline_power_off)
                        .into(imageViewDetail)
                } else {
                    Glide.with(imageViewDetail.context)
                        .load(character.image)
                        .into(imageViewDetail)
                }
            }
            viewBinding.btnSave.setOnClickListener {
                Toast.makeText(context, "Download started...", Toast.LENGTH_SHORT).show()
                val bitmap = viewBinding.imageViewDetail.drawable.toBitmap()
                viewModelDetail.saveImage(requireContext(), bitmap)
            }
            viewBinding.back.setOnClickListener {
                viewModelDetail.back()
            }
            viewBinding.nameOfPersons.text = character.name
        }

        fun observeElement() {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModelDetail.listCharacters.filterNotNull().collect {
                    bind(it)
                }
            }
        }
        observeElement()
    }
}

