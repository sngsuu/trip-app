package com.hwv1.tripapp.util;

public class TripPromptTemplate {

    public static String buildPrompt(String destination, String startDate, String endDate,
                                     String companion, String transportation,
                                     String distance, String mealPreference) {

        return """
아래 조건에 따라 여행 일정을 요약해줘. 
반드시 마크다운 형식을 유지하고, 일일 일정은 날짜와 활동 목록 형식으로 작성해줘.

---

1. 여행 개요
- 목적지: %s
- 여행 기간: %s ~ %s
- 동반자: %s
- 이동 수단: %s
- 하루 이동 거리: %skm 이내
- 식사 취향: %s

2. 일일 일정
- 형식: 아래와 같이 날짜 제목과 하위 활동 리스트 마크다운으로 작성할 것  
  예:
  #### 2025-06-02 (첫째 날)
  - 오전: 예레반 도착, 호텔 체크인
  - 오후: 레푸블리크 광장 탐방
  - 저녁: 전통 음식점에서 식사

  위와 같은 형식으로 하루씩 출력해줘. `#### 날짜`와 `-`로 시작하는 일정이 반드시 포함되어야 해.

3. 추천 장소, 추천 음식, 예상 비용은 마지막에 각 항목별로 리스트 형태로 정리해줘.

---

반드시 위 구조와 형식을 따라야 하며, 표(table)는 사용하지 말고 텍스트 마크다운 리스트로 출력해줘.
""".formatted(
                destination,
                startDate,
                endDate,
                companion != null && !companion.isBlank() ? companion : "단독 여행",
                transportation != null && !transportation.isBlank() ? transportation : "도보, 대중교통",
                distance != null && !distance.isBlank() ? distance : "5",
                mealPreference != null && !mealPreference.isBlank() ? mealPreference : "현지 전통 음식 위주"
        );
    }
}
