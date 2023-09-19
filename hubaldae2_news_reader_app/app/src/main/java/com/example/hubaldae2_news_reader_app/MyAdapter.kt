package com.example.hubaldae2_news_reader_app

import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hubaldae2_news_reader_app.databinding.ItemRecyclerviewBinding

class MyAdapter(val mItems: MutableList<NewsItem>,
                var itemClick : ItemClick? = null // 클릭 호출 관련 인터페이스와 변수(클릭 기능 구현은 mainactivity에서)
                ) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int) // 클릭 호출 관련 인터페이스와 변수(클릭 기능 구현은 mainactivity에서)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }
    // onCreateViewHolder : 선언했던 ViewHolder를 생성. inner class ViewHolder를 여러개 하면, 조건에따라 원하는 ViewHolder를 binding 할 수 있다.

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.Title.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(holder.itemView, position)
            Log.d("ffff","클릭잘됨클릭잘됨")
        }
        holder.Title.text = mItems[position].title
    } // onBindViewHolder : 생성한 ViewHolder의 View에 대해 속성을 바꿈

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    } // getItemCount : 리사이클러뷰로 표현할 아이템의 갯수

    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val Title = binding.title

    } // ViewHolder를 정의. 리사이클러뷰의 .xml에서 어떤 뷰를 binding 할지 정함.
}