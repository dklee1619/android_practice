package com.example.imagesearchpage.data

import android.app.appsearch.SearchResult
import android.content.Context
import android.util.Log
import com.example.imagesearchpage.NetWork.Document
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferences(context: Context) {

    val sharedPreferences = context.getSharedPreferences("pref", 0)
    val editor = sharedPreferences.edit()

    fun loadDocumentList(): MutableList<Document> {
        val jsonString = sharedPreferences.getString("documentList", "")
        return if (jsonString != "") {
            val type = object : TypeToken<MutableList<Document>>() {}.type
            Gson().fromJson(jsonString, type)
        } else {
            mutableListOf()
        }
    }

    fun loadName(): String {
        return sharedPreferences.getString("name", "") ?: ""
    }

    fun saveDocumentList(documentList: MutableList<Document>) {
        val jsonString = Gson().toJson(documentList)
        editor.apply { putString("documentList", jsonString) }.commit()
    }


    fun saveName(name: String) {
        editor.apply { putString("name", name) }.commit()
    }
}

/*
[SharedPreferences 함수 4가지]
1. getSharedPreferences 인스턴스를 생성하고
2. sharedPreferences의 edit 인스턴스를 생성한다.
입력으로 Context를 주어야 하는 이유는 잘 모르겠다.

[SharedPreferences 함수 4가지/데이터 로드를 위한 메서드 2가지]
1. json 데이터(String)를 불러오고, EditText 데이터(String)을 불러오고 바로 뷰의 속성을 바꿔준다.
2. json 데이터를 Kotlin 객체로 변환하고, 변환 타입은 <MutableList<Document>이다. 한편 값이 없으면 텅 빈 MutableListOf() 인스턴스를 생성한다.

[SharedPreferences 함수 4가지/데이터 저장을 위한 메서드2가지]
1. EditText 데이터는 바로 저장하고,
2. Kotlin 객체는 Json 데이터로 변환 후 String 형식으로 저장한다.

[에러: 데이터 저장과 로드가 제대로 안됨]
1. SharedPreferences.Editor의 apply 메서드는 비동기적으로 데이터를 저장한다.
2. commit 메서드는 동기적으로 데이터를 저장한다.
그래서 save 메서드의 끝에 .commit()을 붙여주었다.
 */