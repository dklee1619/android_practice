package com.example.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val asyncButton = findViewById<Button>(R.id.asyncButton)
        val syncButton = findViewById<Button>(R.id.syncButton)

        asyncButton.setOnClickListener {
            val startTime = System.currentTimeMillis()
            for (i in 1..6) {
                synchronousTask(i)
            }
            val endTime = System.currentTimeMillis()
            val resultTime = (endTime - startTime) / 1000
            Log.e("Coroutin : resultTime", "resultTime = $resultTime 초")
        }

        syncButton.setOnClickListener {
            val startTime = System.currentTimeMillis()
            val threadList: MutableList<Thread> = mutableListOf()

            for (i in 1..6) {
                val thread = Thread(Runnable {
                    synchronousTaskrandom(i)
                })
                threadList.add(thread)
            }

            for (i in 0..5) {
                threadList[i].start()
            }
            val endTime = System.currentTimeMillis()
            val resultTime = (endTime - startTime) / 1000
            Log.e("Coroutin : resultTime", "resultTime = $resultTime 초")
        }
    }

    fun synchronousTask(num: Int): String { // 1초 걸림
        Thread.sleep(1000) // 1초 동안 멈춤
        val message = num
        Log.d("Coroutin : TaskNumber", "${message}")
        return "${num}번 작업 완료"
    }

    fun synchronousTaskrandom(num: Int): String { // 1초 걸림
        val message = num
        Thread.sleep(1000 - (500 - Random.nextInt(1000).toLong())) // 0.5~1.5초 동안 멈춤
        Log.d("Coroutin : TaskNumber", "${message}")
        return "${num}번 작업 완료"
    }

}
