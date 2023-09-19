package com.example.hubaldae2_news_reader_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hubaldae2_news_reader_app.NewsRepository.dataList
import com.example.hubaldae2_news_reader_app.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = MyAdapter(dataList, object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val newsItem = dataList[position]
                (activity as MainActivity).onTitleSelected(newsItem)
                val detailFragment = DetailFragment()
                val bundle = Bundle()
                bundle.putString("articleDetail", newsItem.article)
                detailFragment.arguments = bundle
            }
        }
        )

    }

    //    override fun onClick(view: View, position: Int) {
//        Log.d("ffff","클릭잘됨")
//        val item = dataList[position]
//        val fragment = DetailFragment().apply {
//            arguments = Bundle().apply {
//                putString("article", item.article)
//            }
//        }
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.replace(R.id.article, fragment) // DetailFragment에서 fragment_detail.xml의 View를 쓰면 안됨.
//            ?.addToBackStack(null)
//            ?.commit()
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
