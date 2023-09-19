package com.example.fragmentlifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.fragmentlifecycle.databinding.ActivityMainBinding
import com.example.fragmentlifecycle.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
//    private val binding by lazy { ActivitySubBinding.inflate(layoutInflater)}
    private lateinit var binding: ActivitySubBinding // 새로 binding하는 방법 들은대로 해보자
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubBinding.inflate(layoutInflater) // 새로 binding하는 방법 들은대로 해보자
        setContentView(binding.root)
        binding.activitychangesub1.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            MainActivity.stateActivity++
        }
        binding.activitychangesub2.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.activitychangesub3.setOnClickListener {
            finish()
            if (MainActivity.stateActivity >= 2) {
                MainActivity.stateActivity--
            }
        }
        binding.Activitysize.text =  "액티비티 갯수 : " + MainActivity.stateActivity.toString()
    }
}