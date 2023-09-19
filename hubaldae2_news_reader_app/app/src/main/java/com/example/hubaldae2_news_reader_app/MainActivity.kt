package com.example.hubaldae2_news_reader_app

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hubaldae2_news_reader_app.NewsRepository.dataList
import com.example.hubaldae2_news_reader_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            if (isLandscape()) {
                // 가로 모드에서는 이미 XML에 프래그먼트가 정의되어 있으므로 아무 것도 하지 않습니다.
            } else {
                // 세로 모드에서는 TitleFragment만 추가합니다.
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, TitleFragment())
                    .commit()
            }
        }
    }
    private fun isLandscape() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)  // 이걸 추가함으로써 디테일 프래그먼트에서 타이틀 프래그먼트로 돌아갈 수 있음.
        }
    }

    fun onTitleSelected(newsItem: NewsItem) {
        if (isLandscape()) { // isLandscape()는 true면 가로모드 아니면 세로모드임
            val detailFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment) as? DetailFragment
            detailFragment?.updateDetailView(dataList.indexOf(newsItem))
        // 가로모드일때는, 현재 화면에 이미 있는 `DetailFragment`를 찾아 해당 fragment의 내용 업데이트
        //
        } else {
            val newDetailFragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putString("articleDetail", newsItem.article)
                }
            }
            addFragment(newDetailFragment)
        }
    }


}