package com.example.api.SortFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.HomeFragment.HomeViewModel
import com.example.api.R
import com.example.api.Recycler.MyAdapter
import com.example.api.databinding.FragmentHomeBinding
import com.example.api.databinding.FragmentSortBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SortFragment : Fragment(R.layout.fragment_sort) {
    private val viewBinding: FragmentSortBinding by viewBinding()
    private val viewModelSort: SortViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnCancelSort.setOnClickListener {
            viewModelSort.goBack()
        }
        var statusApi = "All"
        var genderApi = "All"
        var speciesApi = "All"
        with(viewBinding) {
            statusAlive.setOnClickListener {
                statusApi = "Alive"
            }
            statusUnknown.setOnClickListener {
                statusApi = "Unknown"
            }
            statusDead.setOnClickListener {
                statusApi = "Dead"
            }
            genderMale.setOnClickListener {
                genderApi = "Male"
            }
            genderFemale.setOnClickListener {
                genderApi = "Female"
            }
            speciesHuman.setOnClickListener {
                speciesApi = "Human"
            }
            speciesAlien.setOnClickListener {
                speciesApi = "Alien"
            }
            btnAcceptSort.setOnClickListener {
                if(statusApi == "All"|| genderApi=="All"|| speciesApi=="All"){
                    Toast.makeText(context,"Choose some shit, bro", Toast.LENGTH_LONG).show()
                }else {
                    viewModelSort.routeToBundle(statusApi, genderApi, speciesApi)
                }
            }
        }
    }
}
