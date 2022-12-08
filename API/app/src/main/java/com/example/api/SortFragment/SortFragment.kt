package com.example.api.SortFragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
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

            statusTxt.background.setColorFilter(Color.parseColor("#B2634BE6"), PorterDuff.Mode.SRC_ATOP)
            genderTxt.background.setColorFilter(Color.parseColor("#B24CDA6B"), PorterDuff.Mode.SRC_ATOP)
            speciesTxt.background.setColorFilter(Color.parseColor("#B2DAA64C"), PorterDuff.Mode.SRC_ATOP)
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
