package com.example.imagesearchpage.data

import com.example.imagesearchpage.data.NetWorkClient.imageNetWork
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface NetWorkInterface {
        @Headers("Authorization: KakaoAK 428f408df29c019a8991a06a6d291b12") // Kakao 인증 헤더를 추가
        @GET("/v2/search/image") // https://dapi.kakao.com/v2/search/image 중 https://dapi.kakao.com/는 BASE_URL이고 나머지 v2/search/image
        suspend fun searchImages(
            @QueryMap param: HashMap<String, String>
//        ): Response
        // 이 메서드는 `Response` 타입의 응답을 반환.
        // https://dapi.kakao.com/v2/search/image?query=[query의 값]&sort=accuracy&page=1&size=80 이란 주소를
        // hashMapOf("query" to query,"sort" to "accuracy","page" to "1","size" to "80") 이란 해쉬맵으로 바꿔줌.
        ): Response
//        { // 응답 수정하기 // 인터페이스에서는 기능을 구현하면 안된다. 기능 분리를 위해 Response를 호출하는곳에서 코드 짜기.
//            val response = NetWorkClient.imageNetWork.searchImages(param)
//            val sortedDocuments = response.documents.sortedByDescending { it.datetime }
//            return Response(response.meta, sortedDocuments)
//        }

}
/*
[서비스 인터페이스 정의]
4. GET에는, NetWorkClient의 BASE_URL 뒤에붙여줄 주소를 적음. 그 주소로 GET 요청을 한다는거
4. 모르겠다~~~ Retrofit은 GPT를통해 해결 이건 GPT한테 도움받고 코드분석할수밖에없는듯!!!!
앞부분 retrofit에 대한 설명은 필요없을거같고 코드 분석이 중요하니까
정예반 세션 앞부분보단 뒷부분이 더 많이 잘 알려주는듯? 시간이 길음 클라이언트랑 인터페이스가 중요하기도하고
 */
