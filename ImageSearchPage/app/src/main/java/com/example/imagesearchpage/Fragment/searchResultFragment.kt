package com.example.imagesearchpage.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearchpage.Adapter
import com.example.imagesearchpage.MainActivity
import com.example.imagesearchpage.R
import com.example.imagesearchpage.data.Document
import com.example.imagesearchpage.data.Response
import com.example.imagesearchpage.databinding.FragmentMyArchiveBinding
import com.example.imagesearchpage.databinding.FragmentSearchResultBinding

class searchResultFragment : Fragment() {
    private var _binding: FragmentSearchResultBinding? =
        null // 1. _binding의 기본값은 null 그리고 null이라 자료형에도 ? 붙음 그리고 var임
    private val binding get() = _binding!! // 2. get()은 커스텀 게더 _binding이 null이 아닐때만 binding에 값이 전달됨
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentSearchResultBinding.inflate(inflater, container, false) // 3. _binding값 지정해주기
        val response: Response? = arguments?.getParcelable("responseData")
        if (response != null) {
            binding.gridview.adapter =
                Adapter(response!!.documents, (object : Adapter.OnItemClickListener {
                    override fun onItemClick(position: Int, document: Document) {
                        if (MainActivity.fragstate) {
                            // ... 나머지 로직
                            if (MainActivity.fragstate) {
                                var state: Boolean = true
                                for (i in 0..MainActivity.item2.size - 1) {
                                    if (document.image_url == MainActivity.item2[i].image_url) {
                                        state = false
                                    }
                                }
                                if (state) {
                                    MainActivity.item2.add(document)
                                }
                                document.favoritestate = true
                            }
                        }
                    }
                }))
//        binding.gridview.layoutManager = LinearLayoutManager(requireContext()) // 이 경우는 수직 리스트입니다.
            binding.gridview.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        return binding.root // 4. 최상위 뷰로 설정
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // 5. 프래그먼트 뷰 사라지면 메모리누수를 방지하기위해 바인딩 풀고 null로
    }
}

// 프래그먼트에서 클릭온 리스너 구현하기, 어댑터쪽은 온클릭 호출??같은거만 하기