package com.chutyrooms.rickandmorty.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chutyrooms.rickandmorty.R
import com.chutyrooms.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.data.entities.Character

class CharacterAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(COMPARATOR) {

    interface OnItemClickListener {
        fun onItemClick(character: Character)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {
            binding.name.text = item.name
            binding.speciesAndStatus.text = """${item.species} - ${item.status}"""
            when (item.status) {
                "Alive" -> binding.status.setBackgroundResource(R.drawable.ic_status_circle_green)
                "unknown" -> binding.status.setBackgroundResource(R.drawable.ic_status_circle_gray)
                "Dead" -> binding.status.setBackgroundResource(R.drawable.ic_status_circle_red)

            }
            val imageLink = item.image
            binding.image.load(imageLink) {
                crossfade(true)
                crossfade(1000)
            }
            binding.root.setOnClickListener {
                // Invoke the callback method with the clicked item
                listener.onItemClick(item)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }

        }
    }


}