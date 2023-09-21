package com.example.imagesearchpage.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearchpage.Adapter
import com.example.imagesearchpage.MainActivity
import com.example.imagesearchpage.NetWork.Document
import com.example.imagesearchpage.data.ItemData
import com.example.imagesearchpage.data.SharedPreferences
import com.example.imagesearchpage.databinding.ActivityMainBinding
import com.example.imagesearchpage.databinding.FragmentMyArchiveBinding

class myArchiveFragment : Fragment() {
    private var _binding: FragmentMyArchiveBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentMyArchiveBinding.inflate(inflater, container, false)
        binding.gridview.adapter = Adapter(
            ItemData.item2,
            (object : Adapter.OnItemClickListener {
                override fun onItemClick(position: Int, document: Document) {
                    ItemDelete(position)
                }
            })
        )

        val LayoutManger = GridLayoutManager(context, 2)
        binding.gridview.layoutManager = LayoutManger
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val mainActivityBinding = (activity as MainActivity).binding
        val textValue = mainActivityBinding.topbarSearch.text.toString()
        ItemData.Pref.apply {
            Log.d("세이브","보관함프래그쪽")
            Log.d("세이브","저장되기 직전의 텍스트는요 : ${textValue}")
            saveDocumentList(ItemData.item2)
            saveName(textValue)
        }
        _binding = null
    }

    fun ItemDelete(position: Int) {
        ItemData.item2.removeAt(position)
        binding.gridview.adapter?.notifyItemRemoved(position)
        Toast.makeText(activity as MainActivity, "보관함에서 데이터가 삭제되었어요!", Toast.LENGTH_SHORT).show()
    }
}
/*
[보관함 프래그먼트]

[리사이클러뷰 어댑터 연결]
1. FragmentMyArchiveBinding를 binding 한 후
2. Adapter를 연결, item2와 Adapter.OnItemClickListener타입의 익명객체를 입력으로 주었다.
3. 익명 객체를 오버라이드한 메서드를 통해, 클릭한 position의 아이템을 삭제하는 함수를 호출하엿다.
[아이템을 삭제하는 로직을 가진 함수]
입력으로 준 아이템이 삭제되었으므로, 리사이클러뷰에게 데이터 변화를 알려주는 메서드를 작성하였다.
이걸 하지 않으면, 아이템의 인덱스 변화에 따른 인덱스가 맞지 않는 에러나, 화면이 올바르게 나타나지 않는 에러가 나게된다.
4. 리사이클러뷰를 2열의 GridView로 주었다.

[데이터 저장]
1. 프래그먼트의 뷰가 파괴될 때 현재 데이터를 저장하는 함수를 호출하고,
2. 데이터 누수를 방지하기 위해 binding을 해제하는 코드를 작성하였다.
*/