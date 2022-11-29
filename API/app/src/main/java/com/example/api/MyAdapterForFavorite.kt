package com.example.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.DBandprovider.PersonDb
import com.example.api.Recycler.MyAdapter
import com.example.api.databinding.RecyclerItemBinding

class MyAdapterForFavorite (private val deletis:(PersonDb) -> Unit): RecyclerView.Adapter<MyAdapterForFavorite.MyViewHolder>() {

    var Items: MutableList<PersonDb> = mutableListOf()
    fun set(items: List<PersonDb>) {
        this.Items = items.toMutableList()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(character: PersonDb) {
            binding.apply {
                nameText.text = character.name
                statusText.text = character.status
                genderText.text = character.gender

                Glide.with(imageView.context)
                    .load(character.image)
                    .into(imageView)
                btnDel.setOnClickListener {
                    deletis(character)
                    notifyDataSetChanged()
                }
//                btnDislike.setOnClickListener {
//                    notifyItemRemoved(position)
//                }
//                btnLike.setOnClickListener {
//                }
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
}