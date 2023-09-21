package com.example.imagesearchpage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.imagesearchpage.NetWork.Document
import com.example.imagesearchpage.data.ItemData
import com.example.imagesearchpage.NetWork.NetWorkClient
import com.example.imagesearchpage.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
        var query = ""
        binding.topbarSearchButton.setOnClickListener {
            query = binding.topbarSearch.text.toString()
            communicateNetWork(setUpDataParameter(query))

        }
        binding.bottombarLeftButton.setOnClickListener {
            bottombarButton("Left")
        }
        binding.bottombarRightButton.setOnClickListener {
            bottombarButton("Right")
        }
    }

    fun bottombarButton(direction: String) {
        if (direction == "Left") {
            binding.bottombarLeftText.setTextColor(Color.parseColor("#6200EE"))
            binding.bottombarLeftImage.setColorFilter(Color.parseColor("#6200EE"))
            binding.bottombarRightText.setTextColor(Color.parseColor("#666666"))
            binding.bottombarRightImage.setColorFilter(Color.parseColor("#666666"))
            ItemData.fragstate = true
            ItemData.searchResultFragment.arguments = ItemData.bundle
            supportFragmentManager.beginTransaction().replace(R.id.Fragment, ItemData.searchResultFragment)
                .commit()

        } else if (direction == "Right") {
            binding.bottombarLeftText.setTextColor(Color.parseColor("#666666"))
            binding.bottombarLeftImage.setColorFilter(Color.parseColor("#666666"))
            binding.bottombarRightText.setTextColor(Color.parseColor("#6200EE"))
            binding.bottombarRightImage.setColorFilter(Color.parseColor("#6200EE"))
            ItemData.fragstate = false
            ItemData.myArchiveFragment.arguments = ItemData.bundle
            supportFragmentManager.beginTransaction().replace(R.id.Fragment, ItemData.myArchiveFragment)
                .commit()
        }
    }

    // 네트워크 통신을 위한 메서드
    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch() {
        try {
            val responseData = NetWorkClient.imageNetWork.searchImages(param) //

            ItemData.bundle.putParcelable("responseData", responseData)

            bottombarButton("Left")
            ItemData.searchResultFragment.updateAdapter(responseData)
        } catch (e: Exception) {
            Log.e("NetworkError", "Error occurred during network call: ${e.message}")
        }
    }

    // 네트워크 요청을 위한 메서드
    private fun setUpDataParameter(query: String): HashMap<String, String> {
        return hashMapOf(
            "query" to query,
            "sort" to "accuracy",
            "page" to "1",
            "size" to "80"
        )
    }

    // 파일에서 JSON을 읽고 Document로 변환하기
    fun loadData(): MutableList<Document> {
        val pref = getSharedPreferences("pref", 0)

        val jsonString = pref.getString("documentList", "")
        binding.topbarSearch.setText(pref.getString("name", ""))
        return if (jsonString != "") {
            val type = object : TypeToken<MutableList<Document>>() {}.type
            Gson().fromJson(jsonString, type)
        } else {
            mutableListOf()
        }
    }

    //JSON으로 변환하고 파일에 저장하기
    fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val jsonString = Gson().toJson(ItemData.item2)

        pref.edit().apply {
            putString("name", binding.topbarSearch.text.toString())
            putString("documentList", jsonString)
            apply()
        }
    }

    fun initialize() {
        ItemData.item2 = loadData()
        binding.apply { // 스코프함수로 묶어서 코드를 정리하자 !
            bottombarLeftText.setTextColor(Color.parseColor("#6200EE"))
            bottombarLeftImage.setColorFilter(Color.parseColor("#6200EE"))
            bottombarRightText.setTextColor(Color.parseColor("#666666"))
            bottombarRightImage.setColorFilter(Color.parseColor("#666666"))
        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }
}
/*
[메인 액티비티]








[추가로 해야될거]

SearchView 찾아보기. (EditText 대신할꺼) 그리고 import 아래꺼로.
SearchView 하나도안이쁨 그냥 EditText 쓰자
더 공부할꺼 : 앱개발 숙련 GridView 의 autofit 등 여러가지속성

위에있는거 탑바,액션바,상단바?

밑에있는게 바텀바,내비게이션바 이거도 알아보기

탭 레이아웃 : 내가 무슨탭에 있어요 라고 알려주기 위해 씀
뷰페이저 알아보기
ViewModel 알아보기

80개 추가로하는거, api 받아오는거 동영상

해야될꺼 다시 정리

1. View Model (+ Live Data, MVVM VS MVC) 한편 이거하면 bundle 쓸필요없어짐 데이터 전달 관련
2. View Pager & Tap Layout
3. SearchView ( Edit Text 대용)
4. TopBar , NavigationBar
5. 80개 추가로 받아오기, 동영상 API도 같이 받아오기
6. 자잘한 버그 수정
 */

