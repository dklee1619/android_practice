package com.example.imagesearchpage.NetWork

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    val meta: Meta, // 응답 관련 정보
    val documents: List<Document> // 응답 결과
) : Parcelable // 이렇게 Parcelable로 해줘야 .putParcelable, .getParcelable로 할 수 있는듯
@Parcelize
data class Meta(
    val total_count: Int, // 검색된 문서 수
    val pageable_count: Int, // total_count 중 노출 가능 문서 수
    val is_end: Boolean // 현재 페이지가 마지막 페이지인지 여부
): Parcelable
@Parcelize
data class Document(
    val collection: String, // 컬렉션
    val thumbnail_url: String, // 미리보기 이미지 URL
    val image_url: String, // 이미지 URL
    val width: Int, // 이미지의 가로 길이
    val height: Int, // 이미지의 세로 길이
    val display_sitename: String, // 출처
    val doc_url: String, // 문서 URL
    val datetime: String, // 문서 작성시간, ISO 8601 형식
    var favoritestate: Boolean = false // 내가 임의로 추가해준 데이터
) : Parcelable

/*
[data class 정의]
1. https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image 에서 데이터를 확인한다.
2. 주소에서 확인한 데이터의 형태에 맞게 데이터를 작성한다.
3. 추가적인 데이터도 작성 가능하다.

[Parcelable 객체로 만들기]
1. bundle과 arguments를 이용해 객체 데이터를 주고받기 위해서는 Parcelable 형태로 만들 필요가 있다.
2. build.gradle (Module :app)의 plugins {
 ...
    id 'kotlin-parcelize'
} 를 추가한다.
3. 데이터 클래스 위에 @Parcelize라는 어노테이션을 달아주고
4. 반환 형태를 Parcelable로 만들어준다.
 */