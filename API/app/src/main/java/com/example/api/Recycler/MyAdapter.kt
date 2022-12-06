package com.example.api.Recycler


import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.databinding.RecyclerItemBinding
import com.example.api.DBandprovider.PersonDb


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

    inner class MyViewHolder(itemBinding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(character: PersonDb) {
            binding.apply {
                val listColor =
                    arrayOf(Color.RED, Color.BLUE, Color.BLACK)
                if (character.status == "Dead") {
                    recitem.setBackgroundColor(listColor[2])
                } else if(character.status == "Alive") {
                    recitem.setBackgroundColor(listColor[1])
                }
                else if (character.status == "unknown") {
                    recitem.setBackgroundColor(listColor[0])
                }
                nameText.text = character.name
                genderText.text = character.gender

                Glide.with(imageView.context)
                    .load(character.image)
                    .into(imageView)
                btnDel.setOnClickListener {
                    delet(character)
                    notifyDataSetChanged()
                }
                if (character.isFavorite) {
                    btnAddToMyFavorite.setColorFilter(Color.YELLOW)
                } else {
                    btnAddToMyFavorite.setColorFilter(Color.WHITE)
                }

                btnAddToMyFavorite.setOnClickListener {
                    addSome(character)
                    notifyDataSetChanged()
                }
                imageView.setOnClickListener {
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
        holder.bind(item[position])

    }

    override fun getItemCount(): Int {
        return item.size
    }
}