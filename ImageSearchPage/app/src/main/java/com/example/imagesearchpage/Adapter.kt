package com.example.imagesearchpage

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearchpage.data.Document
import com.example.imagesearchpage.databinding.ItemRecyclerviewBinding

class Adapter(val item: List<Document>,private val clickListener: OnItemClickListener) : RecyclerView.Adapter<Adapter.Holder>() {
    override fun getItemCount(): Int {
        Log.d("itemitem", "${item.size}")
        return item.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context) // Glide를 활용해 url 주소에 연결된 이미지 binding 하기(좀 더 찾아보고 정리하기)
            .load(item[position].image_url) // Glide를 활용해 크기도 받아올수 있게끔 해보자.
            .into(holder.Image)
//        val aspectRatio = item[position].height.toFloat() / item[position].width.toFloat()
//        holder.Image.layoutParams.width = holder.Image.layoutParams.width
//        holder.Image.scaleTyp
//        holder.Image.layoutParams.height = (holder.Image.layoutParams.width * aspectRatio).toInt()
        holder.Text.text = " [Image] " + item[position].display_sitename // 이거도 값 수정은 옮겨놔도 될듯 dsplay_sitename만 남기기.
        holder.Time.text = item[position].datetime.substring(0, 19).replace("T", " ") // 이거도 substring부터는 옮겨놔도될듯
        if (!item[position].favoritestate) {
            holder.favorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }
        if (item[position].favoritestate) {
            holder.favorite.setImageResource(R.drawable.baseline_favorite_24)
        }

        holder.Image.setOnClickListener { // 셋온클릭리스너를 프래그먼트 쪽으로 옮기기.
            val document = item[position]
            clickListener.onItemClick(position,document) // 어댑터 -> 프래그먼트 클릭리스너 옮기기
//            if (MainActivity.fragstate) {
//                var state:Boolean = true
//                for(i in 0..MainActivity.item2.size-1)
//                {
//                    if(item[position].image_url == MainActivity.item2[i].image_url)
//                    {
//                       state= false
//                    }
//                }
//                if(state){MainActivity.item2.add(item[position])}
//                item[position].favoritestate = true
//            }else if(!MainActivity.fragstate) {
////                MainActivity.item2[position].favoritestate = !MainActivity.item2[position].favoritestate
//                MainActivity.item2.removeAt(position)
//            }
            notifyDataSetChanged() // 화면갱신
        } // 리사이클러뷰 어뎁터에서 클릭 이벤트 구현하기
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    inner class Holder(binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val Image = binding.recyclerImage
        val Text = binding.recyclerText
        val Time = binding.recyclerTime
        val favorite = binding.recyclerFavorite
    }

    interface OnItemClickListener { // 어댑터 -> 프래그먼트 클릭리스너 옮기기 : 어댑터에서 클릭 리스너를 구현하는것은 좋지않아서 프래그먼트에서 사용하기 위한 클릭 리스너를 만드는 인터페이스
        fun onItemClick(position: Int, document: Document)
    }
}

/*
[리사이클러뷰 어댑터]
1. 어댑터의 생성자로 HashMap<String, String> 타입을 줌.
2. 그리고 리사이클러뷰 상속하고, 일단 inner class Holder 생성
3. Holder는 리사이클러뷰의 매커니즘이 되는 ViewHolder 타입이어야할꺼고 ,입력은 binding된 .xml의 최상위뷰 => binding.root
4. 그리고 binding할 .xml은 layout > New > XML > Layout XML File
5. 그리고 3가지 필수적인 메서드들
6. 가장쉬운 getItemCount : 리사이클러뷰를 통해 표현할 총 아이템의 갯수(한 화면 아님!) 바로 return item.size로 바꿔주고
7. onBindViewHolder :
// RecyclerView가 데이터를 해당 위치에 표시할 때 호출된다.
// 데이터를 해당 아이템의 뷰와 바인딩하는 역할을 한다.
 // ViewHolder가 하나일때는, onBindViewHolder에서 데이터를 바인딩해도 상관없지만,
// ViewHolder가 여러개일때는, 제각각의 데이터를 바인딩 할테니까 그냥 inner class 뷰홀더 에서 함수로 만들어주고,
// 그 함수를 onBindViewHolder로 해주어야 한다.
8. onCreateViewHolder :
// viewType을 기반으로 다양한 ViewHolder를 반환.
// onCreateViewHolder에서 최초로 뷰홀더를 생성하고,
// 이후 화면을 스크롤하며 데이터가 바뀌는 부분은 onBindViewHodler를 통해 바뀐다.
9. inner class Holder :
// 그리고 입력으로 넣어준 ViewBinding 을 통해 여러가지 View들에 대해 접근 가능
// onBindViewHolder도 Holder를 입력으로 받아서 binding을 할 수 있지만 보통은 여기서 binding을 한다.
라고 정리를 햇었는데.

10. 일단 리사이클러뷰 예제 코드를 참고하며, 채우긴 했는데 해쉬맵의 형태로 받아와서 position이라고 할게 없는데 어떻게 해야할까?
11. 해쉬맵을 적절하게 리스트의 형태로 변환해주자.
12. 리스트의 형태로 변환할 떄 sorted 같은 정렬을 이용해서, 특정 key의 value에 따라 정렬되게끔 하면 될듯.

 */
