package com.example.api.Recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.R
import com.example.api.databinding.RecyclerItemForInfoBinding

class MyAdapterForInfo(private val plain: (String) -> Unit) :
    RecyclerView.Adapter<MyAdapterForInfo.MyViewHolder>() {

    var item: MutableList<String> = mutableListOf()
    fun set(items: String) {
        val list = listOf(items,items,items,items,items)
        this.item = list.toMutableList()
        notifyDataSetChanged()
    }

     class MyViewHolder(itemBinding: RecyclerItemForInfoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(character: String, plain: (String) -> Unit) {
            binding.apply {
                if (character == null) {
                    Glide.with(imageViewInfo.context)
                        .load(R.drawable.ic_baseline_star_24)
                        .into(imageViewInfo)
                } else {
                    Glide.with(imageViewInfo.context)
                        .load(character)
                        .into(imageViewInfo)
                }
                imageViewInfo.setOnClickListener {
                    plain(character)
                }
            }
            return
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            RecyclerItemForInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(item[position], plain)

    }

    override fun getItemCount(): Int {
        return item.size
    }
}