package com.example.imagesearchpage.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagesearchpage.Adapter
import com.example.imagesearchpage.R
import com.example.imagesearchpage.data.Response
import com.example.imagesearchpage.databinding.FragmentMyArchiveBinding

class myArchiveFragment : Fragment() {
    private var _binding: FragmentMyArchiveBinding? = null // 1. _binding의 기본값은 null 그리고 null이라 자료형에도 ? 붙음 그리고 var임
    private val binding get() = _binding!! // 2. get()은 커스텀 게더 _binding이 null이 아닐때만 binding에 값이 전달됨
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyArchiveBinding.inflate(inflater, container, false) // 3. _binding값 지정해주기

        return binding.root // 4. 최상위 뷰로 설정
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 5. 프래그먼트 뷰 사라지면 메모리누수를 방지하기위해 바인딩 풀고 null로
    }
}