// ✅ 여행 요약 응답 DTO (파일명: TripPlanResponse.java)
package com.hwv1.tripapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TripPlanResponse {
    private String summary; // 여행 개요 텍스트
    private List<DailyPlan> dailyPlan; // 일별 일정 목록
    private List<String> costs; // 비용 항목 문자열 리스트

    @Data
    @AllArgsConstructor
    public static class DailyPlan {
        private String date; // ex) "6월 9일 (첫 번째 날)"
        private List<String> activities; // 해당 날짜의 일정 활동 리스트
    }
}
