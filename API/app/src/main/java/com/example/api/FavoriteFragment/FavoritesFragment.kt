package com.example.api.FavoriteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.Recycler.MyAdapterForFavorite
import com.example.api.R
import com.example.api.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val viewBinding: FragmentFavoritesBinding by viewBinding()
    private val viewModelFavorite: FavoritesViewModel by viewModel()
    private var adapterFavorite: MyAdapterForFavorite? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeElement()
        adapterFavorite = MyAdapterForFavorite { viewModelFavorite.delFavoritePerson(it.id) }
        with(viewBinding.recyclerViewFavorite) {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
            )
            adapter = adapterFavorite
        }
        viewBinding.back.setOnClickListener {
            viewModelFavorite.back()
        }
    }

    private fun observeElement() {
        viewModelFavorite.listFavorite.onEach {
            adapterFavorite?.set(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}