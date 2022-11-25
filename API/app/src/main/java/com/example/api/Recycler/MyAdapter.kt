package com.example.api.Recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.Character1
import com.example.api.CharacterList
import com.example.api.R

import com.example.api.databinding.RecyclerItemBinding
import retrofit2.Response


class MyAdapter (private val delet:(Character1) -> Unit,private val addSome:(Character1) -> Unit): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var Items: MutableList<Character1> = mutableListOf()
    fun set(items: List<Character1>) {
        this.Items = items.toMutableList()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(character: Character1) {
            binding.apply {
                nameText.text = character.name
                statusText.text = character.status
                genderText.text = character.gender

                 Glide.with(imageView.context)
                    .load(character.image)
                    .into(imageView)
                    btnDel.setOnClickListener {
                       delet(character)
                        notifyDataSetChanged()
                    }
                    btnAddToMyFavorite.setOnClickListener {
                        addSome(character)
                        notifyDataSetChanged()
                    }
            }
            return
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(Items[position])

    }

    override fun getItemCount(): Int {
        return Items.size
    }

//    fun set(items: List<SomethingDb>){
//        this.Items = listOf()
//        this.Items = items
//        notifyDataSetChanged()
//    }


}