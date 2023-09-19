package com.example.imagesearchpage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.imagesearchpage.Fragment.myArchiveFragment
import com.example.imagesearchpage.Fragment.searchResultFragment
import com.example.imagesearchpage.data.NetWorkClient
import com.example.imagesearchpage.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var fragstate: Boolean = true
    val searchResultFragment = searchResultFragment()
    val myArchiveFragment = myArchiveFragment()
    var query = ""
    lateinit var List:List<Pair<String, String>>
    val bundle:Bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.topbarSearchButton.setOnClickListener {
            query = binding.topbarSearch.text.toString()
            communicateNetWork(setUpDataParameter(query))

        }
        binding.bottombarLeftButton.setOnClickListener {
            val direction = "Left"
            bottombarButton(direction)
        }
        binding.bottombarRightButton.setOnClickListener {
            val direction = "Right"
            bottombarButton(direction)
        }
    }

    fun bottombarButton(direction: String) {
        if (direction == "Left") {
            binding.bottombarLeftText.setTextColor(Color.parseColor("#6200EE"))
            binding.bottombarLeftImage.setColorFilter(Color.parseColor("#6200EE"))
            binding.bottombarRightText.setTextColor(Color.parseColor("#666666"))
            binding.bottombarRightImage.setColorFilter(Color.parseColor("#666666"))
            fragstate = true
            searchResultFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.Fragment,searchResultFragment).commit()
        } else if (direction == "Right") {
            binding.bottombarLeftText.setTextColor(Color.parseColor("#666666"))
            binding.bottombarLeftImage.setColorFilter(Color.parseColor("#666666"))
            binding.bottombarRightText.setTextColor(Color.parseColor("#6200EE"))
            binding.bottombarRightImage.setColorFilter(Color.parseColor("#6200EE"))
            fragstate = false
            myArchiveFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.Fragment,myArchiveFragment).commit()
        }
    }
    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch() {
        // HashMap을 받아서,
        // 한편, onCreate로 시작하는 메인 쓰레드가 있고, 이 함수는 그 바깥 영역에 있는데
        // 데이터를 주고받는 과정에서 렉이 걸리면, 화면이 멈추게 됨. 그래서 메인쓰레드 바깥에 만들어준거. 이거는 room때도 설명함
        try {
        val responseData = NetWorkClient.imageNetWork.searchImages(param) //



            bundle.putParcelable("responseData",responseData)
        Log.d("Parsing Dust ::", responseData.toString())
        // data class에서의 Retrofit 을 통해서 dataclass가 생성이 됨. 네트워크 요청을 통해 받은 Gson인가 json 데이터가 우리가 사용하고자 하는 data class로 변환이 된다.

        } catch (e: Exception) {
            Log.e("NetworkError", "Error occurred during network call: ${e.message}")
        }
    }
    // 네트워크 요청을 위한 메서드
     private fun setUpDataParameter(query: String): HashMap<String, String>{
        val authKey = "428f408df29c019a8991a06a6d291b12" // 일반적인 카카오 API 호출에 사용되는 "일반 인증키"는 "REST API 키"를 의미
        return hashMapOf( // 요청변수 그리고 항목명 & 샘플데이터
//            "Authorization" to "KakaoAK $authKey", // @Headers("Authorization: KakaoAK 428f408df29c019a8991a06a6d291b12")로 추가해 주었음.
            "query" to query, // query는 내가 edit 텍스트로 입력해줄 값임.
            "sort" to "accuracy",
            "page" to "1",
            "size" to "80"
        )
    }

}

/*
1. binding과 gradle부터..
[레이아웃 만들기]
2. 레이아웃 만들기 : 2개의 프래그먼트와, 프래그먼트를 전환할 수 있는 버튼 추가
2. 레이아웃 만들다, 뒷배경 사각형 주는거 까먹어서 구글링으로 검색하고 shape에 대해 정리함
2. button은 색이 맘대로 되질 않아서 androidx.appcompat.widget.AppCompatButton로 수정
2. 하단바의 검색결과와 내 보관함에 대해, 영역을 반씩 차지하게끔 하는 방법을 몰랐다가 Guideline에 대해 알게됨.
2. 그리고 그 Guideline을
2. 그리고 그리드뷰도 추가해야됨
2. 코틀린 코드에 버튼 클릭시 이미지와 텍스트#BB00DA(보라색,현재 탭) #666665(회색,아닌 탭)
[데이터와 리사이클러뷰어댑터 만들기]
3. build.gradle (Module :app)에 라이브러리 추가하기
3. 매니페스트에 http통신을 위한 사용권한 추가하기

3. Retrofit 예제코드 일단 그대로 따라하기(2개의 클래스파일)
3. https://velog.io/@ywown/kotlin-%ED%86%B5%EC%8B%A0-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%ACVolley-Retrofit보고 따라하기
3. 데이터 클래스 정의 (https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image의Document 그대로가져옴)
3. 서비스 인터페이스 정의 (잘 모르겠음)
3. Retrofit 객체 생성

3. Retrofit 처음부터 다시
3. 우선 https://developers.kakao.com/console/app 여기서 애플리케이션 추가하기로,
3. REST API 키 : 428f408df29c019a8991a06a6d291b12 를 발급받았다.
3. 네트워크 요청을 위한 메서드 MainActivity에 추가.

3. 다시 차근차근 정리 참고사이트 : https://velog.io/@ywown/kotlin-%ED%86%B5%EC%8B%A0-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%ACVolley-Retrofit#2-retrofit-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC
3. Retrofit 라이브러리 선언 O
3. [모델 클래스 선언] - data class에서
 */