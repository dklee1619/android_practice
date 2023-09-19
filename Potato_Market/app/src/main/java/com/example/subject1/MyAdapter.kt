package com.example.subject1

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.subject1.databinding.ItemRecyclerviewBinding

class MyAdapter(val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnLongClickListener {
            val currentPosition = holder.adapterPosition // 현재 holder의 어댑터 위치를 가져옴
            // 롱 클릭 이벤트가 발생할 때의 로직을 여기에 작성합니다.
            val builder = AlertDialog.Builder(it.context)  // 여기서 it는 holder.itemView를 가리킵니다.
            builder.setTitle("상품 삭제")
            builder.setIcon(R.drawable.ic_dialog_chat)

            val v1 = LayoutInflater.from(it.context).inflate(R.layout.dialog, null)
            val dialogTextView: TextView = v1.findViewById(R.id.dialogtext)
            dialogTextView.text = "상품을 정말로 삭제하시겠습니까?"
            builder.setView(v1)

            val listener = DialogInterface.OnClickListener { dialogInterface, i -> /* 원하는 액션을 수행 */
                // 데이터 삭제
                mItems.removeAt(currentPosition)

                // RecyclerView에 항목이 삭제되었음을 알림
                notifyItemRemoved(currentPosition)
            }
            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", null)
            builder.show()

            // 아래의 true는 롱 클릭 이벤트가 처리되었음을 시스템에 알리는 것입니다.
            // 만약 false를 반환하면, 일반 클릭 이벤트도 함께 발생할 수 있습니다.
            true
        }
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }
        holder.iconImageView.setImageResource(mItems[position].aIcon)
        holder.productName.text = mItems[position].productName
        holder.address.text = mItems[position].address
        holder.price.text =  String.format("%,d 원", mItems[position].price)
        holder.likes.text = mItems[position].likes.toString()
        holder.chatting.text = mItems[position].chatting.toString()
    }

//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val productName = binding.productName
        val address = binding.address
        val price = binding.price
        val likes = binding.favorite2Text
        val chatting = binding.chatText

    }

}