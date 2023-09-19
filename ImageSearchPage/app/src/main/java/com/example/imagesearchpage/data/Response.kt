package com.example.imagesearchpage.data

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
): Parcelable

/*
[모델 클래스 선언]
3. 응답 본문은 meta, documents로 구성된 JSON 객체라고 한다.
3. Meta라는 데이터 클래스는, 3가지 파라미터를 포함한다.
3. Document라는 데이터 클래스는, 8가지 파라미터를 포함한다.
3. Response는 Meta라는 자료형과 List<Document>라는 자료형을 가지는 2가지 파라미터로 구성되있다.
*/