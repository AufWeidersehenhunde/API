package com.example.api.HomeFragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.Recycler.MyAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {
    private var adapterHome: MyAdapter? = null
    private val viewBinding: FragmentHomeBinding by viewBinding()
    private val viewModelHome: HomeViewModel by viewModel()
    companion object {
        private const val STATUS = "status"
        private const val GENDER = "gender"
        private const val SPECIES = "species"
        fun getInstance(statusApi: String,genderApi:String,speciesApi:String) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString(STATUS, statusApi)
                putString(GENDER, genderApi)
                putString(SPECIES, speciesApi)
            }
        }
    }

    private fun initObservers(){
        observeElement()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initViewSort()
    }

    private fun initView(){

        this.adapterHome =
            MyAdapter({ viewModelHome.delPerson(it) }, { viewModelHome.putInFavorite(it.id) }, {viewModelHome.routeToInfo(it.id)})

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
                containerMenu.isVisible = !containerMenu.isVisible
                if (btnExit.visibility == View.VISIBLE){
                    btnExit.background.setColorFilter(Color.parseColor("#99C62C20"), PorterDuff.Mode.SRC_ATOP)
                }
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

        viewBinding.searchView.setOnQueryTextListener(this@HomeFragment)

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
    }

    private fun initViewSort(){
        val myStatus = arguments?.getString(STATUS)
        val myGender = arguments?.getString(GENDER)
        val mySpecies = arguments?.getString(SPECIES)
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
        viewModelHome.listCharacters.onEach {
            adapterHome?.set(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty())
            viewModelHome.searchAny("%$newText%")
        return true
    }
}
