package com.example.imagesearchpage.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imagesearchpage.Adapter
import com.example.imagesearchpage.MainActivity
import com.example.imagesearchpage.data.Document
import com.example.imagesearchpage.databinding.FragmentMyArchiveBinding

class myArchiveFragment : Fragment() {
    private var _binding: FragmentMyArchiveBinding? =
        null // 1. _binding의 기본값은 null 그리고 null이라 자료형에도 ? 붙음 그리고 var임
    private val binding get() = _binding!! // 2. get()은 커스텀 게더 _binding이 null이 아닐때만 binding에 값이 전달됨
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentMyArchiveBinding.inflate(inflater, container, false) // 3. _binding값 지정해주기
        binding.gridview.adapter = Adapter(
            MainActivity.item2,
            (object : Adapter.OnItemClickListener {
                override fun onItemClick(position: Int, document: Document) {
                    MainActivity.item2.removeAt(position)
                    binding.gridview.adapter?.notifyDataSetChanged() // 데이터 갱신을 해주지 않으면
                    // java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter positionHolder 에러 발생
                    // 리사이클러뷰의 데이터 세트와 내부 상태가 불일치할 때 발생
//                    val indexToRemove = MainActivity.item2.indexOf(document)
//
//                    if (indexToRemove != -1) {
//                        // 아이템 제거
//                        MainActivity.item2.removeAt(indexToRemove)
//                        // 리사이클러뷰에 알려주기
//                        binding.gridview.adapter?.notifyItemRemoved(indexToRemove)
//                    }
                }
            })
        )

//        binding.gridview.layoutManager = LinearLayoutManager(requireContext()) // 이 경우는 수직 리스트입니다.
        binding.gridview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        return binding.root // 4. 최상위 뷰로 설정
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).saveData() // 보관함 나가면 자동저장
        _binding = null // 5. 프래그먼트 뷰 사라지면 메모리누수를 방지하기위해 바인딩 풀고 null로
    }

}

//