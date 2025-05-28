package com.hwv1.tripapp.dto;

import java.util.List;

/**
 * 여행 계획 응답 DTO
 * 클라이언트에게 반환할 여행 일정 결과를 담는다
 */
public class TripPlanResponse {

    private List<String> dailyPlan;   // 일자별 계획 (간단한 문자열 리스트로 표현)
    private int estimatedCost;        // 예상 비용 (원화 기준)
    private String summary;           // 전체 여행 요약

    // Getter / Setter
    public List<String> getDailyPlan() {
        return dailyPlan;
    }

    public void setDailyPlan(List<String> dailyPlan) {
        this.dailyPlan = dailyPlan;
    }

    public int getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(int estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
