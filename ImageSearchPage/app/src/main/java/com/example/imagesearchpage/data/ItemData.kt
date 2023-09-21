package com.example.imagesearchpage.data

import android.os.Bundle
import com.example.imagesearchpage.Fragment.myArchiveFragment
import com.example.imagesearchpage.Fragment.searchResultFragment
import com.example.imagesearchpage.NetWork.Document

object ItemData {
    var fragstate: Boolean = true
    var item2: MutableList<Document> = mutableListOf()
    val searchResultFragment = searchResultFragment()
    val myArchiveFragment = myArchiveFragment()
    val bundle: Bundle = Bundle()
    lateinit var Pref: SharedPreferences
}

/*
[ItemData]
한 번 인스턴스를 생성하면 계속 사용할 객체나, 혹은 여러 클래스에서 쓰이는 변수를 ItemData에 나눠주었다.
 */