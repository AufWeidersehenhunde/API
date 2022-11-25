package com.example.api

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.HomeFragment.HomeViewModel
import com.example.api.Recycler.MyAdapter
import com.example.api.databinding.FragmentFavoritesBinding
import com.example.api.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val viewBinding: FragmentFavoritesBinding by viewBinding()
    private val viewFF: FavoritesViewModel by viewModel()
    private var someAdapterFavorite:MyAdapterForFavorite? = null

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private fun goToView() {
        viewFF.listCharactersFavorite.observe(viewLifecycleOwner){
            someAdapterFavorite?.set(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        someAdapterFavorite = MyAdapterForFavorite ({viewFF.delSomething(it)})
        with(viewBinding.recyclerViewFavorite) {
            layoutManager = GridLayoutManager(
                context,2
            )
            adapter = someAdapterFavorite
        }
        viewBinding.btnBack.setOnClickListener {
            viewFF.backFragment()
        }
            viewFF.getCharactersFavorite()
        goToView()
    }
}