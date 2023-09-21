package com.example.imagesearchpage.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearchpage.Adapter
import com.example.imagesearchpage.NetWork.Document
import com.example.imagesearchpage.data.ItemData
import com.example.imagesearchpage.NetWork.Response
import com.example.imagesearchpage.databinding.FragmentSearchResultBinding

class searchResultFragment : Fragment() {
    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentSearchResultBinding.inflate(inflater, container, false)
        val response: Response? = arguments?.getParcelable("responseData")
        if (response != null) {
            updateAdapter(response)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun updateAdapter(response: Response) {
        binding.gridview.adapter =
            Adapter(
                response.documents.sortedByDescending { it.datetime },
                (object : Adapter.OnItemClickListener {
                    override fun onItemClick(position: Int, document: Document) {
                        ItemAdd(document)
                    }
                })
            )
        val LayoutManger = GridLayoutManager(context, 2)
        binding.gridview.layoutManager = LayoutManger
    }

    fun ItemAdd(document: Document) {
        if (!document.favoritestate) {
            if (ItemData.fragstate) {
                var state: Boolean = true
                for (i in 0..ItemData.item2.size - 1) {
                    if (document.image_url == ItemData.item2[i].image_url) {
                        state = false
                    }
                }
                if (state) {
                    ItemData.item2.add(document)
                }
                document.favoritestate = true
            }
        } else if (document.favoritestate) {
            document.favoritestate = false
            for (i in 0..ItemData.item2.size - 1) {
                if (document.image_url == ItemData.item2[i].image_url) {
                    ItemData.item2.remove(document)
                }
            }
        }
    }
}
/*
[검색 결과 프래그먼트]

[데이터 받기]
1. bundle 인스턴스에 저장된 Response 타입의 객체를 받아온다. 객체를 받아오기 위해 Response를 포함한 데이터 클래스들을
@Parcelize를 통해 Parcelable 형태로 만들어 주었다.
2. 전달된 데이터가 null이 아닐경우, 화면을 갱신하는 함수를 호출한다.

[화면을 갱신하는 함수]
1. 바인딩된 프래그먼트의, 리사이클러뷰의 어댑터에 대해, 앞에서 받은 Response 객체의 Document에 대해
Document의 datatime에 따라 List를 내림차순 정렬하게끔 한 Response 객체의 Document 입력으로 주었고
2. 클릭시 아이템을 추가하는 로직을 가진 함수를 호출하였다.

[아이템을 추가하는 로직을 가진 함수]
1. 좋아요가 false면 true로 바꿔준 후, 데이터를 추가하는 로직을 짠다.
state를 true로 선언해준 후, 반복문을 통해 image_url이 같은 데이터가 있는지 확인 후, 있으면 state를 false로 만든다.
그리고 state가 true일때만 데이터를 추가해준다.
화면은 따로 갱신해주지 않는다. 어짜피 추가된 데이터는 현재 프래그먼트에서 아무런 영향을 끼치지 않기 때문이다.
2. 좋아요가 true면 false로 바꿔준 후, 반복문을 통해 image_url이 같은 데이터가 있는지 확인 후, 있으면 그 데이터를 삭제해준다.
*/