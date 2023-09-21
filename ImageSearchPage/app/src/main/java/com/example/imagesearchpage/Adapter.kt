package com.example.imagesearchpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearchpage.NetWork.Document
import com.example.imagesearchpage.databinding.ItemRecyclerviewBinding

class Adapter(val item: List<Document>, private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<Adapter.Holder>() {
    override fun getItemCount(): Int {
        return item.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(item[position].image_url)
            .override(item[position].width, item[position].height)
            .into(holder.Image)

        holder.Text.text = " [Image] " + item[position].display_sitename
        holder.Time.text = item[position].datetime.substring(0, 19).replace("T", " ")
        if (!item[position].favoritestate) {
            holder.favorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }
        if (item[position].favoritestate) {
            holder.favorite.setImageResource(R.drawable.baseline_favorite_24)
        }

        holder.Image.setOnClickListener {
            val document = item[position]
            clickListener.onItemClick(position, document)
            this.notifyItemChanged(position)
        }
    }

    inner class Holder(binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val Image = binding.recyclerImage
        val Text = binding.recyclerText
        val Time = binding.recyclerTime
        val favorite = binding.recyclerFavorite
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, document: Document)
    }

}

/*
[리사이클러뷰 어댑터]

 */
