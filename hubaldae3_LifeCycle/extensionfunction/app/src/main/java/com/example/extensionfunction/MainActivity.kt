package com.example.extensionfunction

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.extensionfunction.databinding.ActivityMainBinding
import java.time.Duration

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var time: Int = Toast.LENGTH_SHORT // 함수에도 들어가니까 전역변수로 선언 기본값은 2초로
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.toasttime.setOnClickListener {
            if (time == Toast.LENGTH_SHORT) {
                time = Toast.LENGTH_LONG
                binding.toasttime.text = "토스트 메세지를 띄울 시간 : 3.5초"
            } else if (time == Toast.LENGTH_LONG) {
                time = Toast.LENGTH_SHORT
                binding.toasttime.text = "토스트 메세지를 띄울 시간 : 2초"
            }
        }
        binding.toastplay.setOnClickListener {
            showToast(binding.messageinput.getText().toString(), time)
        }
    }

    fun Context.showToast(text: String, duration: Int) {
        Toast.makeText(this, text + "를 ${if(time==1){3.5}else{2}}초간 표기합니다", duration).show()
    }
}
/*
이전에 Toast 클래스를 아무리 확인하고 개발자 문서를 찾아봐도, 블로그를 찾아봐도 시간 자체를 바꾸는 방법은 모르겠어서 포기.

1. 에디트 텍스트로 입력할 메세지와 시간 설정(0또는1)버튼  그리고 검사
2. 버튼 누르면 데이터 바인딩
3. 버튼2 누르면 토스트메시지 띄우기
 */