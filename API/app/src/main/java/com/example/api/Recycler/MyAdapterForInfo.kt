package com.example.api.Recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.DBandprovider.PersonDb
import com.example.api.databinding.NewRecyclerItemBinding


class MyAdapterForInfo() : RecyclerView.Adapter<MyAdapterForInfo.MyViewHolder>() {
    var item: MutableList<PersonDb> = mutableListOf()
    fun set(items: List<PersonDb>) {
        this.item = items.toMutableList()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemBinding: NewRecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(character: PersonDb) {
            binding.apply {
                fun statusColor() {
                    if (character.status == "Alive") {
                        statusInfo.setTextColor(Color.GREEN)
                    } else if (character.status == "unknown") {
                        statusInfo.setTextColor(Color.CYAN)
                    } else {
                        statusInfo.setTextColor(Color.RED)
                    }
                }
                fun genderColor(){if (character.gender == "Male") {
                    genderInfo.setTextColor(Color.RED)
                } else {
                    genderInfo.setTextColor(Color.BLUE)
                }
            }
               fun speciesColor() {if(character.species == "Human"){
                    speciesInfo.setTextColor(Color.YELLOW)
                } else{
                speciesInfo.setTextColor(Color.MAGENTA)}}


                nameInfo.text = "Name: ${character.name}"
                genderColor()
                genderInfo.text = "Gender: ${character.gender}"
                statusColor()
                statusInfo.text = "Status: ${character.status}"
                speciesColor()
                speciesInfo.text = "Species: ${character.species}"

                Glide.with(imageViewInfo.context)
                    .load(character.image)
                    .into(imageViewInfo)
                btnSave.setOnClickListener {
                    notifyDataSetChanged()
                }
            }
            return
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            NewRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(item[position])

    }

    override fun getItemCount(): Int {
        return item.size
    }
}




