package com.example.imagesearchpage.NetWork

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap


interface NetWorkInterface {
    @Headers("Authorization: KakaoAK 428f408df29c019a8991a06a6d291b12") // Kakao 인증 헤더를 추가
    @GET("/v2/search/image") // https://dapi.kakao.com/v2/search/image 중 https://dapi.kakao.com/는 BASE_URL이고 나머지 v2/search/image
    suspend fun searchImages(
        @QueryMap param: HashMap<String, String>
    ): Response
}
/* 동작의 순서
[@QueryMap]
1. HashMap 데이터 예를들어 {"query" = 입력값, "sort" = "accuracy", "page" = "1", "size" = "80"}를 입력으로 받으면,
2. @QueryMap에 의해 query=IVE&sort=accuracy&page=1&size=80 란 값이 된다.

[@GET]
1. 'Retrofit 객체를 생성할 때 입력한 기본 URL' + '@GET에 입력해준 값' + '@QueryMap이 반환한 값'을 합치고
2. 그 주소로부터 데이터를 요청한다.

[@Headers]
0. 요청 URL을 생성
1. 요청 헤더를 설정 (이 때 @Headers에 정의된 헤더 값들이 사용)
2. 실제 HTTP 요청을 수행(@GET(""))
카카오 키를 깃이그노어로 안올라가게 막아버리는거 해두기

[결론]
query라는 변수명에 담긴 String 데이터를 입력 받아서, Response라는 이름을 가지는 Kotlin 객체를 반환한다.

*/