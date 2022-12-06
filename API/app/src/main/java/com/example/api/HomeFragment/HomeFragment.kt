package com.example.api.HomeFragment

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.graphics.rotationMatrix
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.internal.findRootView
import com.example.api.Recycler.MyAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var adapterHome: MyAdapter? = null
    private val viewBinding: FragmentHomeBinding by viewBinding()
    private val viewModelHome: HomeViewModel by viewModel()
    companion object {
        private const val DATA = "status"
        private const val GENDER = "gender"
        private const val SPECIES = "species"
        fun getInstance(statusApi: String,genderApi:String,speciesApi:String) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString(DATA, statusApi)
                putString(GENDER, genderApi)
                putString(SPECIES, speciesApi)
                println("ikzgf$statusApi,gen$genderApi,hen$speciesApi")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myStatus = arguments?.getString(DATA)
        val myGender = arguments?.getString(GENDER)
        val mySpecies = arguments?.getString(SPECIES)

        observeElement()
        adapterHome =
            MyAdapter({ viewModelHome.delPerson(it) }, { viewModelHome.getItFavorite(it.id) }, {viewModelHome.routeToInfo(it.id)})

        with(viewBinding.recyclerView) {
            layoutManager = LinearLayoutManager(
                context
            )
            adapter = adapterHome
        }

        viewBinding.back.setOnClickListener {
            viewModelHome.back()
        }


        viewBinding.menu.setOnClickListener{
            with(viewBinding){
                btnFavorite.isVisible = !btnFavorite.isVisible
                btnSorting.isVisible = !btnSorting.isVisible
                btnExit.isVisible = !btnExit.isVisible

                menu
                    .animate()
                    .rotation(
                        if (btnExit.isVisible){
                            90f
                        } else {
                            0f
                        }
                    )
                    .setDuration(255L)
                    .start()
            }
        }
        viewBinding.btnSorting.setOnClickListener{
            viewModelHome.goToSorting()
        }


        viewBinding.btnExit.setOnClickListener {
            Toast.makeText(context, "Please,  вернись", Toast.LENGTH_LONG).show()
            fun onDestroy() {
                super.onDestroy();
                System.runFinalizersOnExit(true);
                System.exit(0);
            }
            onDestroy()
        }

        viewBinding.btnFavorite.setOnClickListener {
            viewModelHome.toFavorite()
        }

        if (!myStatus.isNullOrEmpty() || !myGender.isNullOrEmpty() || !mySpecies.isNullOrEmpty()) {
            viewModelHome.viewSortPersons(
                myStatus.toString(),
                myGender.toString(),
                mySpecies.toString()
            )
        }else {
            viewModelHome.observeAllPersons()
        }
    }
    private fun observeElement() {
        viewModelHome._listCharacters.onEach {
            adapterHome?.set(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

}
