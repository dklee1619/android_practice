package com.example.hubaldae2_news_reader_app



data class NewsItem(
    val title: String, // This is title
    val article: String // This is article
)
object NewsRepository {
    val dataList: MutableList<NewsItem> = mutableListOf()

    init {
        dataList.add(
            NewsItem(
                title = "과학자들, 새로운 행성 발견!",
                article = "최근 국제 천문학자 그룹이 새로운 행성을 발견했다. 이 행성은 태양계 바깥에 위치하며, 지구와 유사한 조건을 가지고 있다고 밝혀졌다."
            )
        )

        dataList.add(
            NewsItem(
                title = "스마트폰 시장, 변화의 바람",
                article = "최근에 출시된 스마트폰들은 새로운 기술과 혁신적인 디자인으로 전세계의 주목을 받고 있다. 특히 접는 스마트폰의 인기가 높아지고 있다."
            )
        )

        dataList.add(
            NewsItem(
                title = "올해의 유니콘 스타트업 선정!",
                article = "이번년도 유니콘 스타트업으로 선정된 회사들이 발표되었다. 이들 회사는 차세대 기술을 선도하며 높은 성장성을 보이고 있다."
            )
        )

        dataList.add(
            NewsItem(
                title = "기후 변화, 심각한 위협",
                article = "전세계 기후 변화로 인한 문제가 점점 심각해지고 있다. 많은 나라들이 이 문제에 대한 해결책을 찾기 위해 노력 중이다."
            )
        )

        dataList.add(
            NewsItem(
                title = "스포츠 스타, 자선 활동에 나서",
                article = "국내외 유명 스포츠 스타들이 자선 활동에 나서, 사회적 책임을 다하고 있다. 이들의 활동은 많은 사람들에게 큰 영감을 주고 있다."
            )
        )

        dataList.add(
            NewsItem(
                title = "로봇 기술, 새로운 시대를 열다",
                article = "최근 로봇 기술의 발전으로 다양한 분야에서의 활용 가능성이 확대되고 있다. 특히 의료, 제조, 서비스 분야에서 큰 변화가 기대된다."
            )
        )

        dataList.add(
            NewsItem(
                title = "VR과 AR, 새로운 엔터테인먼트의 중심으로",
                article = "가상현실(VR)과 증강현실(AR) 기술이 급속도로 발전하면서 엔터테인먼트 분야의 새로운 트렌드로 떠오르고 있다."
            )
        )

        dataList.add(
            NewsItem(
                title = "자동차 산업의 전기차 시대",
                article = "자동차 산업이 전기차로의 전환을 가속화하고 있다. 많은 제조사들이 새로운 전기차 모델을 출시하며 경쟁을 펼치고 있다."
            )
        )

        dataList.add(
            NewsItem(
                title = "우주 관광, 더 이상 꿈만은 아니다",
                article = "일부 기업들이 우주 관광을 실현시키기 위한 계획을 세우고 있다. 이에 따라 미래에는 일반인도 우주를 경험할 수 있을 것으로 예상된다."
            )
        )

        dataList.add(
            NewsItem(
                title = "데이터 과학, 21세기의 가장 핫한 직업",
                article = "데이터 과학과 관련된 직업이 최근 몇 년 동안 빠르게 성장하며 많은 사람들의 관심을 받고 있다. 특히 AI와 머신러닝 분야에서의 수요가 높다."
            )
        )
    }

    val newsList = NewsRepository.dataList
}
