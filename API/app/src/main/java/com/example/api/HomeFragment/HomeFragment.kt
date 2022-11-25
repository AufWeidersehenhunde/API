package com.example.api.HomeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.InfoFragment.InfoFragment
import com.example.api.Recycler.MyAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.api.R
import com.example.api.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var someAdapter: MyAdapter? = null
    private val viewBinding: FragmentHomeBinding by viewBinding()
    private val viewMH: HomeViewModel by viewModel()
    companion object {
        fun newInstance() = InfoFragment()
    }

    private fun goToView() {
        viewMH.listCharacters.observe(viewLifecycleOwner){
            someAdapter?.set(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewMH.getCharacters(1)
        someAdapter = MyAdapter({viewMH.delSomething(it)},{viewMH.takeThis(it)})
        with(viewBinding.recyclerView) {
            layoutManager = GridLayoutManager(
                context,2
            )
            adapter = someAdapter
        }
        viewBinding.btnFavorite.setOnClickListener {
            viewMH.favorite()
        }
        goToView()
        viewBinding.button.visibility = View.INVISIBLE
        viewBinding.textView.visibility = View.INVISIBLE

        viewBinding.sibalnahoi.setOnClickListener {
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
