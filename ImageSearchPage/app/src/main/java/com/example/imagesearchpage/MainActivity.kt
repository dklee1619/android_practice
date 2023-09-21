package com.example.imagesearchpage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.imagesearchpage.data.ItemData
import com.example.imagesearchpage.NetWork.NetWorkClient
import com.example.imagesearchpage.data.SharedPreferences
import com.example.imagesearchpage.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
        binding.topbarSearchButton.setOnClickListener {
            communicateNetWork(setUpDataParameter(binding.topbarSearch.text.toString()))
        }
        binding.bottombarLeftButton.setOnClickListener {
            bottombarButton("Left")
        }
        binding.bottombarRightButton.setOnClickListener {
            bottombarButton("Right")
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
    fun bottombarButton(direction: String) {
        if (direction == "Left") {
            binding.apply {
                bottombarLeftText.setTextColor(Color.parseColor("#6200EE"))
                bottombarLeftImage.setColorFilter(Color.parseColor("#6200EE"))
                bottombarRightText.setTextColor(Color.parseColor("#666666"))
                bottombarRightImage.setColorFilter(Color.parseColor("#666666"))
            }
            ItemData.fragstate = true
            supportFragmentManager.beginTransaction()
                .replace(R.id.Fragment, ItemData.searchResultFragment).commit()

        } else if (direction == "Right") {
            binding.apply {
                bottombarLeftText.setTextColor(Color.parseColor("#666666"))
                bottombarLeftImage.setColorFilter(Color.parseColor("#666666"))
                bottombarRightText.setTextColor(Color.parseColor("#6200EE"))
                bottombarRightImage.setColorFilter(Color.parseColor("#6200EE"))
            }
            ItemData.fragstate = false
            supportFragmentManager.beginTransaction()
                .replace(R.id.Fragment, ItemData.myArchiveFragment).commit()
        }
    }

    fun initialize() {
        ItemData.Pref = SharedPreferences(this)
        ItemData.Pref.apply {
            ItemData.item2 = loadDocumentList()
            binding.topbarSearch.setText(loadName())
        }
        ItemData.searchResultFragment.arguments = ItemData.bundle
        binding.apply { // 스코프함수로 묶어서 코드를 정리하자 !
            bottombarLeftText.setTextColor(Color.parseColor("#6200EE"))
            bottombarLeftImage.setColorFilter(Color.parseColor("#6200EE"))
            bottombarRightText.setTextColor(Color.parseColor("#666666"))
            bottombarRightImage.setColorFilter(Color.parseColor("#666666"))
        }
    }

}
/*
[메인 액티비티]
1. 초기 설정과, 셋온 클릭 리스너를 선언한다.
그리고 5가지 함수가 있다.

[네트워크 함수 2가지]
[네트워크 함수 2가지/네트워크 통신을 위한 메서드]
1. retrofit 객체를 통해 HashMap 형태의 입력을 주면 데이터 클래스 형태의 객체를 반환한다.
2. 반환된 객체를 bundle에 담는다.
3. 검색 결과 프래그먼트로 전환하고, 통신을 통해 받은 데이터에 맞게 화면을 갱신해준다
.
[네트워크 함수 2가지/네트워크 요청을 위한 메서드]
1. EditText에 입력한 변수를 HashMap 형태로 반환한다.

[하단바 버튼 기능 함수]
1. 어떤 방향의 버튼을 클릭하느냐에 따라 텍스트뷰와 이미지뷰가 다르게 변하게 하였다.
2. 어떤 프래그먼트를 표시하고 있는지 알려줄 변수를 갱신하였고,
3. supportFragmentManager를 통해 레이아웃에 연결된 프래그먼트를 변경하였다.

[초기 설정 함수]
1. 보관함의 데이터를 불러오는 함수를 호출하고,
2. 검색결과의 argument에 bundle 인스턴스를 연결하였다.
3. 버튼의 색상을 지정해주었다.



[추가로 해야될거]

SearchView 찾아보기. (EditText 대신할꺼) 그리고 import 아래꺼로.
SearchView 하나도안이쁨 그냥 EditText 쓰자
더 공부할꺼 : 앱개발 숙련 GridView 의 autofit 등 여러가지속성

위에있는거 탑바,액션바,상단바?

밑에있는게 바텀바,내비게이션바 이거도 알아보기 // 내비게이션바나 뷰페이저를 썻으면, (확실하지는 않은데) 온크리에이트가 안되서 갱신이 안될수도있음.
그러면, 따로 Notify 어쩌구 메서드를 호출해서 공지를 해줄 필요가 생김

탭 레이아웃 : 내가 무슨탭에 있어요 라고 알려주기 위해 씀
뷰페이저 알아보기
ViewModel 알아보기

80개 추가로하는거, api 받아오는거 동영상

에디트텍스트까지 프래그먼트 하나로 묶어두면, 번들을 쓸필요가 없다(하나의 프래그먼트니까) 이런방법도 있다는것 알아두고 가자.
해야될꺼 다시 정리

1. SharedPreferences 따로 클래스 파서 만들기
1. View Model (+ Live Data, MVVM VS MVC) 한편 이거하면 bundle 쓸필요없어짐 데이터 전달 관련
2. View Pager & Tap Layout
3. SearchView ( Edit Text 대용)
4. TopBar , NavigationBar
5. 80개 추가로 받아오기, 동영상 API도 같이 받아오기
6. 자잘한 버그 수정
 */

