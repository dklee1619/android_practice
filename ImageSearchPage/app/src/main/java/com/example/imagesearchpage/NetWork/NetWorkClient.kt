package com.example.imagesearchpage.NetWork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetWorkClient {
    private const val BASE_URL = "https://dapi.kakao.com/"

    private val imageRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val imageNetWork: NetWorkInterface = imageRetrofit.create(NetWorkInterface::class.java)
}

/*
[변수 설정]
1. 기본 주소를 설정한다

[Retrofit 객체 생성]
1. Retrofit 객체를 생성한다. 객체는 Retrofit.Builder().build()를 통해 생성되고,
2. Builder() 인스턴스는 Retrofit의 각종 설정을 하는데 이용되고
3. build() 인스턴스는 Retrofit 객체를 생성하는데 이용된다.

[Retrofit 객체의 설정]
1. .baseUrl(String데이터)는 기본 주소를 설정한다.
2. .addConverterFactory(GsonConverterFactory.create()) 는 서버로부터 (.xml이나 Json) 데이터를 받으면 Kotlin 객체로 변환하게끔 설정한다.

[Retrofit 인스턴스 생성]
1. NetWorkInterface 타입의 Retrofit 객체를 생성한다

okHttp는 나중에 retrofit 코드를 외워서 쓸수 있을때 넣으려고 뺏다. 어짜피 통신환경이 원할하면 굳이 없어도 되는걸 확인하였기 때문
*/