package com.example.api.HomeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api.Recycler.MyAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var Adapter: MyAdapter? = null
    private val viewBinding: FragmentHomeBinding by viewBinding()
    private val viewModelHome: HomeViewModel by viewModel()

    private fun observeElement() {
        viewModelHome.getCharacters(1)
        viewModelHome.observeAllPersons().observe(viewLifecycleOwner) {
            Adapter?.set(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Adapter =
            MyAdapter({ viewModelHome.delPerson(it) }, { viewModelHome.getItFavorite(it.uuid) })
        with(viewBinding.recyclerView) {
            layoutManager = GridLayoutManager(
                context, 2
            )
            adapter = Adapter
        }
        viewBinding.btnFavorite.setOnClickListener {
            viewModelHome.favorite()
        }
        observeElement()
        viewBinding.button.visibility = View.INVISIBLE
        viewBinding.textView.visibility = View.INVISIBLE

        viewBinding.btnOff.setOnClickListener {
            Toast.makeText(context, "Please  вернись", Toast.LENGTH_LONG).show()
            fun onDestroy() {
                super.onDestroy();
                System.runFinalizersOnExit(true);
                System.exit(0);
            }
            onDestroy()
        }

    }

}
