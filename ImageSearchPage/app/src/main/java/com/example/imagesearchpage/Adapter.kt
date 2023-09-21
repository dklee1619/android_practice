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
[입력]
1. Document 타입의 List와 OnItemClickListener 타입의 변수를 입력해준다.
Document는 retrofit을 통해 받아온 자료의 일부분이고, OnItemClickListener는 여기서 인터페이스를 만들어 준 후,
나중에 프래그먼트에서 어뎁터의 인터페이스를 상속한 익명객체를 통해 onItemClick을 구현하게 된다.
2. RecyclerView.Adapter<Adapter.Holder>()를 상속 받는다. 리사이클러뷰니까 당연한거고
Adapter와 Holder는 자기 자신과 자기자신의 내부클래스인 Holder를 말한다.

[getItemCount()](메서드, item.size를 반환)
1. 별다른 사정이 없으니까 입력으로 준 List의 사이즈만큼을 반환한다.

[onCreateViewHolder](메서드, Holder(binding)를 반환)
1. 리사이클러뷰의 .xml을 바인딩 한 후 Adapter의 내부 클래스인 Holder를 반환한다.

[Holder](클래스)
1. RecyclerView.ViewHolder(binding.root)를 상속받는다. 따라서 기본적인 ViewHolder의 동작을 한다.
2. binding.root이므로 입력으로 준 binding의 최상위 뷰를 ViewHolder의 입력으로 준다.
3. Holder에서는 입력으로준 binding의 View 요소들을 변수에 담는다.

[onBindViewHolder]
1. 입력으로 Holder와 Position을 받아서, 아이템의 Position에 맞는 데이터를 Holder에 바인딩해준다.
2. Glide 객체는 build.gradle(Module :app)에 라이브러를 추가해서 사용해야 한다.
implementation 'com.github.bumptech.glide:glide:4.15.1'
annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
추가해준 이유는, URL로 받은 이미지 데이터를 표현하기 위해서.
3. 그 외엔 각 View의 속성을 바꿔주거나, setOnClickListener{}를 구현해주었다.
4. clickListener.onItemClick(position, document)의 기능은 프래그먼트에서 구현하였다.
5. 클릭시 이미지가 항상 변하기 때문에, this.notifyItemChanged(position)를 추가해주었다.

[OnItemClickListener]
1. 기능 구현은 프래그먼트에서 하기 위해 추가해준 인터페이스
2. 익명 객체로 어댑터의 인터페이스를 상속받아서 기능을 구현한다.
 */
