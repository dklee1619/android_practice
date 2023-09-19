package com.example.fragmentlifecycle

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fragmentlifecycle.databinding.ActivityMainBinding
import java.time.Duration

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var state = 0 // 함수에도 들어가기 때문에 전역 변수로 선언을 해준다.
    val BlankFragment = BlankFragment()
    val BlankFragment2 = BlankFragment2()

    companion object { // companion object로 변수를 생성하면, 다른 클래스에서도 이 변수를 호출이 가능하다.
        var stateActivity = 1
        val bundle: Bundle = Bundle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.AddFragment.setOnClickListener {
            if (BlankFragment._binding == null) {
                AddFrag(BlankFragment, 1)
            } // 타입에 따라 추가하는 프래그먼트가 다르기 때문에 2번째 인자를 만들어 주었다.
        }
        binding.DeleteFragment.setOnClickListener {
            DeleteFrag(R.id.Fragment)  // 프래그먼트 추가는 입력이 프래그먼트 클래스고, 프래그먼트 삭제는 입력이 .xml 파일이다.
        }
        binding.AddFragmentNew.setOnClickListener {
            AddFrag(BlankFragment(), 1)
        }
        binding.AddFragment2.setOnClickListener {
            if (BlankFragment2._binding == null) {
                AddFrag(BlankFragment2, 2)
            }
        }
        binding.AddFragmentNew2.setOnClickListener {
            AddFrag(BlankFragment2(), 1)
        }
        binding.replaceFragment.setOnClickListener {
            ReplaceFrag(BlankFragment, 1)
        }
        binding.replaceFragment2.setOnClickListener {
            ReplaceFrag(BlankFragment2, 2)
        }
        binding.activitychangemain1.setOnClickListener {
            intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
            stateActivity++
        }
        binding.activitychangemain2.setOnClickListener {
            intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.activitychangemain3.setOnClickListener {
            intent = Intent(this, SubActivity::class.java)
            finish()
            if (stateActivity >= 2) {
                stateActivity--
            }
        }
        binding.gohome.setOnClickListener {
            val homeIntent = Intent(Intent.ACTION_MAIN) // 홈 화면으로 가는 4줄의 코드
            homeIntent.addCategory(Intent.CATEGORY_HOME) // 참고한 사이트 : https://darksilber.tistory.com/298
            homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(homeIntent)
        }
        binding.Fragmentsize.text = "프래그먼트 갯수 : ${state}"
        binding.Activitysize.text = "액티비티 갯수 : " + stateActivity.toString()
    }

    fun ReplaceFrag(frag: Fragment, type: Int) {
        bundleFrag(frag)
        if ((type == 1) && (state != 0)) { // 아무거도 존재하지 않아도 replace를 통해 프래그먼트를 바꿀 수 있지만,
            // 그래도 존재하지 않을때는 실행되지 않게끔
            supportFragmentManager.beginTransaction()
                .replace(R.id.Fragment, frag)
                .setReorderingAllowed(true) // 트랜잭션과 관련된 프래그먼트의 상태 변경을 최적화
//                .addToBackStack(null) // 은 트랙잭션이 백 스택에 커밋됨. 쉽게 설명하면 뒤로가기 누르면 이전의 프래그먼트를 불러올수 있음. 혹은 String형 데이터를 입력하고, popBackStack()을 통해 원하는 프래그먼트를 불러올 수 있음.
                // 대신, onDestroyView까지만 실행되고 onDestroy는 실행되지 않게됨. 그래서 불러올 수 있는것 같음.
                .commit()
            state = 1
            binding.Fragmentsize.text = "프래그먼트 갯수 : ${state}"
        } else if ((type == 2) && (state != 0)) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.Fragment, frag)
                .setReorderingAllowed(true)
//                .addToBackStack(null)
                .commit()
            state = 1
            binding.Fragmentsize.text = "프래그먼트 갯수 : ${state}"
        }
    }

    fun AddFrag(frag: Fragment, type: Int) {
        bundleFrag(frag)
        if ((type == 1)) {
            supportFragmentManager.beginTransaction()
                .add(R.id.Fragment, frag)
                .setReorderingAllowed(true)
//                .addToBackStack(null)
                .commit()
            state++
            binding.Fragmentsize.text = "프래그먼트 갯수 : ${state}"
        } else if ((type == 2)) {
            supportFragmentManager.beginTransaction()
                .add(R.id.Fragment, frag)
                .setReorderingAllowed(true)
//                .addToBackStack(null)
                .commit()
            state++
            binding.Fragmentsize.text = "프래그먼트 갯수 : ${state}"
        }
    }

    fun DeleteFrag(Ridlayout: Int) {
        if (state != 0) { // 프래그먼트가 존재하지 않는 상태에서 제거를 하면 'Runtime Error'가 걸린다.
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.findFragmentById(Ridlayout)!!)
                // 다른데서는 입력으로 프래그먼트 클래스를 주는데, remove는 레이아웃을 입력으로 준다. .xml과 binding된 모든 Fragment를 제거해주는 듯 싶다.
                // 즉, 주인공이 레이아웃이어서 입력을 .xml을 주는듯 하다.

                .commit()
            if (state > 0) {
                state--
                binding.Fragmentsize.text = "프래그먼트 갯수 : ${state}"
            }
        }
    }

    fun bundleFrag(frag: Fragment) {
//        frag.arguments?.putString("key", binding.mainedit.text.toString()) // 어귀먼트에 번들 데이터 담기
        bundle.putString("key", binding.mainedit.text.toString())
        frag.arguments = bundle
    }
}












/*
1. Activity에는 Fragment를 추가하거나 제거하는 버튼들이 있어야 한다고 한다.
2. 프래그먼트를 호출해고 제거를 하면 될거같은데, 감이 안잡히네. 강의를 확인해 보아야겠다.
3. 프래그먼트 클래스 만들기( New > Flagment > BlankFragment) 그리고 코드 지울꺼 지우기
4. 액티비티에는 프래그먼트를 호출할 버튼과 프래그먼트를 표시할 영역이 필요하다. .xml에 만들어주자.
5. 2개의 버튼과 FrameLayout 추가
6. build.gradle(Module :app)과 액티비티,프래그먼트에 binding 코드 추가
7. 셋온 클릭리스너까지 잘 추가
8. 프래그먼트는 바인딩 어떻게했는지 잘 기억이 안난다.
9. 프래그먼트는 액티비티에서 관리하는 함수를 만들어줘야 하는것 같다.
10. 그리고 build.gradle(Module :app) 에도 implementation 'androidx.fragment:fragment-ktx:1.6.1' 추가해줘야함.
11. 프래그먼트를 호출하고 제거하는 방법을 잘 모르겠다. 강의의 예제는 단순히 전환하는것이여서. 구글링해보자
12. 우여곡절끝에 supportFragmentManager에 대한 자료를 얻을수 잇었고,
13. 우선 삭제한 후에도 삭제버튼을 누르면 오류가 나므로 관리하는 변수를 만들기(if문으로)
14. 우선 추가하고 삭제하면 onSaveInstanceState 뺴고는 잘 된다.
15. onViewStateRestored() & onSaveInstanceState() 후자에서 저장하면 전자에서 불러올수 있는데,
16. 나는 추가와 삭제이므로 다시 코드를 짜보자. 2개의 프래그먼트로 해야할것 같은데.
17. 혹은 bundle & argument로 데이터 전달하고 받고 하면 될줄 알았는데 안됨. 액티비티로 데이터 전달을 해보자.
18. 데이터 전달로 하는것 실패..!
19. onSaveInstanceState는 가로<->세로 모드 전환, 어플 강제종료, 홈화면으로 등등 비정상적인 종료 등이 일어날때 발생

1. 프래그먼트에서는 버튼을 만들어도 동작을 안함.
2. 데이터클래스로 만들어줫다가, 왜 state().Acitivtystate ++ 이렇게해도 증가가 안할까 생각하다가,
그냥 매번 새로운 데이터클래스 인스턴스를 호출했다는것을 알게 됨..
3. 그래서 이전에 GPT가 짜준 코드를 분석하다 알게된 Companion object로 변수를 선언함.
4. 액티비티는 화면이 전환될 떄 onCreate()에서 바로 액티비티수가 맞게 갱신이 되지만,
프래그먼트는 추가할때마다 새로 바인딩을 해줘야해서, state다음에 쭉 바인딩을 추가함

(.addToBackStack(null) 없을때)
1. 프래그먼트 생성 : onCreate() -> onCreateView() -> onViewCreated() -> onViewStateResotred() -> onStart() -> onResume()
2. 2번째 액티비티로(StartActivity()) : onStop() -> onSaveInstanceStart()
3. 2번째 액티비티종료(finish()) : onStart() -> onResume()
4. 1번째 액티비티종료(finish()) : onPasue() -> onStop() -> onDestroyView() -> onDestroy()

(.addToBackStack(null) 있을때)
1. 프래그먼트 생성 : onCreate() -> onCreateView() -> onViewCreated() -> onViewStateResotred() -> onStart() -> onResume()
2. 프래그먼트 삭제 : onPasue() -> onStop() -> onDestroyView() -> onDestroy()

인스턴스화한 번들 하나하나마다 다른건가?
왜 2번째 프래그를 만들어야 bundle에 담기는거지? => bundle,argument 인스턴스 생성하고 호출하는 과정을 올바르게 안해서. 지금은 일단 과정자체를 암기해야겠다.
그리고, 이제 변환하면 왜 edit가 안담기는지 확인해보자. => bundle 인스턴스를 하나만 생성하고 그 bundle을 계속 이용하니 잘 된다.
그런데, 액티비티를 뉴인스턴스로 생성해도 담기는거도 조금 불편한데.



거쳤던 문제들 생각해보기.
1. 처음에 만든 인스턴스 하나만 쓰는것과 새로운 인스턴스가 계속 생성되는 차이
2. 액티비티 -> 프래그먼트 데이터 전달하기 와 프래그먼트 -> 프래그먼트 데이터 전달하기의 올바른 과정
3. supportFragmentManager.beginTransaction()을 통해 프래그먼트 관리하기. add & replace & delete 그리고 addToBackStack(null) & setReorderingAllowed(true)
4. 액티비티와 프래그먼트의 바인딩 하는 방법이 다르단것
5. (activity as MainActivity).BlankFragment2.arguments 이런 방식으로 메인액티비티의 변수를 사용하는것과
5. companion object{ var stateActivity = 1 } , MainActivity.stateActivity 이런 방식으로 메인액티비티의 변수 사용하는것
6.
 */