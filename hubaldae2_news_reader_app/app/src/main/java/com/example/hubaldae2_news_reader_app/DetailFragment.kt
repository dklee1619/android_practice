package com.example.hubaldae2_news_reader_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hubaldae2_news_reader_app.NewsRepository.dataList
import com.example.hubaldae2_news_reader_app.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        // Bundle에서 데이터를 가져와서 TextView에 적용합니다.
        arguments?.let {
            val articleDetail = it.getString("articleDetail")
            binding.article.text = articleDetail // 이 부분은 Binding 객체를 사용하여 TextView를 참조하고 있습니다.
        }

        return binding.root
    }
    // 이 함수를 호출하여 세부 내용을 업데이트 합니다.
    fun updateDetailView(position: Int) {
        val newsItem = dataList[position]
        binding.article.text = newsItem.article
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

