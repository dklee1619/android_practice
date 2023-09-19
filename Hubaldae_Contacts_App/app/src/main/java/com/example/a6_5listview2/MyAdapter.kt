package com.example.a6_5listview2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a6_5listview2.databinding.HiddenItemLayoutBinding
import com.example.a6_5listview2.databinding.ItemRecyclerviewBinding
import com.example.a6_5listview2.databinding.ItemRecyclerviewFavoriteBinding

// 사용자 정의 RecyclerView 어댑터. Contact 리스트와 아이템 클릭 리스너를 매개변수로 받습니다.
// 1. MyAdapter라는 클래스는 RecyclerView.Adapter<RecyclerView.ViewHolder>()를 상속받음.
// 2.
class MyAdapter(
    val mItems: MutableList<Contact>,
    val listener: OnItemClickListener,
    private val recyclerViewType: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    // 숫자 매핑을 위한 MutableMap 예제
//    var intMap: MutableMap<String, Int> = mutableMapOf("one" to 1, "two" to 2)
//
//    // "seven"이라는 키가 없을 때 기본값 0을 반환
//    val value = intMap.getOrDefault("seven", 0)

    // 아이템 클릭 인터페이스
    interface OnItemClickListener {
        fun onFavoriteImageViewClicked(position: Int, contact: Contact) {

        }
    }


    // 뷰 유형에 따라 적절한 ViewHolder를 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_FAVORITE -> {
                val binding = ItemRecyclerviewFavoriteBinding.inflate(inflater, parent, false)
                FavoriteViewHolder(binding)
            }

            VIEW_TYPE_NORMAL -> {
                val binding = ItemRecyclerviewBinding.inflate(inflater, parent, false)
                NormalViewHolder(binding)
            }

            VIEW_TYPE_HIDDEN -> {
                val binding = HiddenItemLayoutBinding.inflate(inflater, parent, false)
                HiddenViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    // ViewHolder를 데이터와 바인딩
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mItems[position]
        when (holder.itemViewType) {
            VIEW_TYPE_FAVORITE -> (holder as FavoriteViewHolder).bind(item)
            VIEW_TYPE_NORMAL -> (holder as NormalViewHolder).bind(item)
        }

    }

//    // 고유 ID를 반환 (여기선 위치 사용)
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }

    // 데이터셋의 크기를 반환
    override fun getItemCount(): Int {
        return mItems.size
    }

    // 상수 정의
    companion object {
        private const val VIEW_TYPE_FAVORITE = 1
        private const val VIEW_TYPE_NORMAL = 2
        private const val VIEW_TYPE_HIDDEN = 3
    }

    // 해당 위치의 뷰 타입 결정
    override fun getItemViewType(position: Int): Int {
        val item = mItems[position]
        if (recyclerViewType == 1) { // 첫 번째 리사이클러뷰
            return if (item.isFavorite) VIEW_TYPE_FAVORITE else VIEW_TYPE_HIDDEN
        } else { // 두 번째 리사이클러뷰
            return if (item.isFavorite) VIEW_TYPE_FAVORITE else VIEW_TYPE_NORMAL
        }
    }

    // 일반 아이템에 대한 ViewHolder
    inner class NormalViewHolder(val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.favoritenot.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onFavoriteImageViewClicked(position, mItems[position])
                }
            }
        }

        // 데이터 바인딩
        fun bind(item: Contact) {
            binding.PhotoCircle.setImageResource(item.PhotoCircle)
            binding.textName.text = item.textName
            binding.textPhoneNumber.text = item.PhoneNumber
            if (item.isFavorite) {
                binding.favorite.visibility = View.VISIBLE
                binding.favoritenot.visibility = View.GONE
            } else {
                binding.favorite.visibility = View.GONE
                binding.favoritenot.visibility = View.VISIBLE
            }
        }
    }

    // 즐겨찾기 아이템에 대한 ViewHolder
    inner class FavoriteViewHolder(val binding: ItemRecyclerviewFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.favorite.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onFavoriteImageViewClicked(position, mItems[position])
                }
            }
        }

        // 데이터 바인딩
        fun bind(item: Contact) {
            binding.PhotoCircle.setImageResource(item.PhotoCircle)
            binding.textName.text = item.textName
            binding.textPhoneNumber.text = item.PhoneNumber
            if (item.isFavorite) {
                binding.favorite.visibility = View.VISIBLE
                binding.favoritenot.visibility = View.GONE
            } else {
                binding.favorite.visibility = View.GONE
                binding.favoritenot.visibility = View.VISIBLE
            }
        }
    }

    inner class HiddenViewHolder(val binding: HiddenItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
        }
    }
}

// 1. 과제를 직접 해야하는 이유 : override fun onCreateViewHolder, onBindViewHolder, getItemId, getItemCount
// 2. 이런것들이 자동생성 되는건데 이것들을 잘 몰랐으니까.
// 3.

