package com.example.api.Recycler

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.DBandprovider.PersonDb
import com.example.api.databinding.RecyclerItemBinding


class MyAdapter(
    private val delet: (PersonDb) -> Unit,
    private val addSome: (PersonDb) -> Unit,
    private val info: (PersonDb) -> Unit
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var item: List<PersonDb> = listOf()
    fun set(items: List<PersonDb>) {
        this.item = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(
            character: PersonDb,
            delet: (PersonDb) -> Unit,
            addSome: (PersonDb) -> Unit,
            info: (PersonDb) -> Unit
        ) {
            binding.apply {
                if (character.status == "Dead") {
                    recitem.background.setColorFilter(Color.parseColor("#99F3EDED"), PorterDuff.Mode.SRC_ATOP)
                } else if(character.status == "Alive") {
                    recitem.background.setColorFilter(Color.parseColor("#99429DE6"), PorterDuff.Mode.SRC_ATOP)
                }
                else if (character.status == "unknown") {
                    recitem.background.setColorFilter(Color.parseColor("#99C62C20"), PorterDuff.Mode.SRC_ATOP)
                }
                nameText.text = character.name
                genderText.text = character.gender

                Glide.with(imageView.context)
                    .load(character.image)
                    .into(imageView)
                btnDel.setOnClickListener {
                    delet(character)
                }
                if (character.isFavorite) {
                    btnAddToMyFavorite.setColorFilter(Color.YELLOW)
                } else {
                    btnAddToMyFavorite.setColorFilter(Color.WHITE)
                }

                btnAddToMyFavorite.setOnClickListener {
                    addSome(character)
                }
                recitem.setOnClickListener {
                    info(character)
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
        holder.bind(item[position], delet, addSome, info)
    }

    override fun getItemCount(): Int {
        return item.size
    }
}