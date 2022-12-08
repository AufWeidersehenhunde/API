package com.example.api.InfoFragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.DBandprovider.PersonDb
import com.example.api.R
import com.example.api.Recycler.MyAdapterForInfo
import com.example.api.databinding.FragmentInfoBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class InfoFragment : Fragment(R.layout.fragment_info) {
    private var infoAdapter: MyAdapterForInfo? = null
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uuidForInfo = arguments?.getInt(DATA)
        if (uuidForInfo != null) {
            viewModelInfo.getInfoFragment(uuidForInfo.toInt())
        }
        infoAdapter =
            MyAdapterForInfo { image -> viewModelInfo.routeToDetail(image, uuidForInfo!!) }

        with(viewBinding.recyclerViewForInfo) {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = infoAdapter
        }


        fun bind(character: PersonDb) {
            viewBinding.apply {
                fun statusColor() {
                    if (character.status == "Alive") {
                        statusInfo.setTextColor(Color.parseColor("#8A4FC922"))
                    } else if (character.status == "unknown") {
                        statusInfo.setTextColor(Color.parseColor("#8A181A18"))
                    } else {
                        statusInfo.setTextColor(Color.parseColor("#B7D83F3F"))
                    }
                }
                back.setOnClickListener {
                    viewModelInfo.back()
                }
                fun genderColor() {
                    if (character.gender == "Male") {
                        genderInfo.setTextColor(Color.parseColor("#B7D83F3F"))
                    } else {
                        genderInfo.setTextColor(Color.parseColor("#99429DE6"))
                    }
                }

                fun speciesColor() {
                    if (character.species == "Human") {
                        speciesInfo.setTextColor(Color.parseColor("#8AB64EB6"))
                    } else {
                        speciesInfo.setTextColor(Color.parseColor("#8A9AB64E"))
                    }
                }
                genderColor()
                genderInfo.text = "Gender: ${character.gender}"
                statusColor()
                statusInfo.text = "Status: ${character.status}"
                speciesColor()
                speciesInfo.text = "Species: ${character.species}"

                nameOfPersons.text = character.name
            }
        }


        fun observeElement() {
            viewModelInfo.takePersonsImage()
            viewModelInfo.listCharacters.onEach {
                it?.image?.let { it1 -> infoAdapter?.set(it1) }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        }
        observeElement()
        fun observeElemento() {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModelInfo.listCharacters.filterNotNull().collect {
                    bind(it)
                }
            }

        }
        observeElemento()
    }
}
