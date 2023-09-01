package com.example.a6_5listview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a6_5listview2.databinding.ItemRecyclerviewBinding
import com.example.a6_5listview2.databinding.ItemRecyclerviewFavoriteBinding

class MyAdapter(val mItems: MutableList<Contact>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var intMap: MutableMap<String, Int> = mutableMapOf("one" to 1, "two" to 2)

    val value = intMap.getOrDefault("seven",0)

    interface OnItemClickListener {
        fun onItemClicked(position: Int, contact: Contact)
    }


    var itemClick : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NormalViewHolder(binding)
            }
            VIEW_TYPE_FAVORITE -> {
                val binding = ItemRecyclerviewFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FavoriteViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mItems[position]

        when (holder) {
            is NormalViewHolder -> {
                holder.bind(item)
            }
            is FavoriteViewHolder -> {
                holder.bind(item)
            }
            else -> throw IllegalArgumentException("Unknown ViewHolder type: ${holder::class.java}")
        }
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    companion object {
        private const val VIEW_TYPE_NORMAL = 0
        private const val VIEW_TYPE_FAVORITE = 1
    }
    override fun getItemViewType(position: Int): Int {
        return if (mItems[position].isFavorite) {
            VIEW_TYPE_FAVORITE
        } else {
            VIEW_TYPE_NORMAL
        }
    }
    inner class NormalViewHolder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClicked(position, mItems[position])
                }
            }
        }
        fun bind(item: Contact) {
            binding.PhotoCircle.setImageResource(item.PhotoCircle)
            binding.textName.text = item.textName
            binding.textPhoneNumber.text = item.PhoneNumber
        }
    }

    inner class FavoriteViewHolder(val binding: ItemRecyclerviewFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClicked(position, mItems[position])
                }
            }
        }
        fun bind(item: Contact) {
            binding.PhotoCircle.setImageResource(item.PhotoCircle)
            binding.textName.text = item.textName
            binding.textPhoneNumber.text = item.PhoneNumber
        }
    }

}

// 1. 과제를 직접 해야하는 이유 : override fun onCreateViewHolder, onBindViewHolder, getItemId, getItemCount
// 2. 이런것들이 자동생성 되는건데 이것들을 잘 몰랐으니까.
// 3.