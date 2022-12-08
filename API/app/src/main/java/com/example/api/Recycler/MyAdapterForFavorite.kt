package com.example.api.Recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.DBandprovider.PersonDb
import com.example.api.databinding.RecyclerItemBinding

class MyAdapterForFavorite(private val remove: (PersonDb) -> Unit) :
    RecyclerView.Adapter<MyAdapterForFavorite.MyViewHolder>() {

    var item: MutableList<PersonDb> = mutableListOf()
    fun set(items: List<PersonDb>) {
        this.item = items.toMutableList()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(character: PersonDb) {
            binding.apply {
                nameText.text = character.name
                genderText.text = character.gender

                Glide.with(imageView.context)
                    .load(character.image)
                    .into(imageView)
                btnDel.setOnClickListener {
                    remove(character)
                    notifyDataSetChanged()
                }
                binding.btnAddToMyFavorite.isVisible = false
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
        holder.bind(item[position])

    }

    override fun getItemCount(): Int {
        return item.size
    }
}