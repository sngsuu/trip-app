package com.hwv1.tripapp.dto;

import java.util.List;

/**
 * 여행 계획 요청 DTO
 * 클라이언트로부터 받은 입력 데이터를 담는다
 */
public class TripPlanRequest {

    private String destination;  // 여행 목적지
    private String startDate;    // 여행 시작일
    private String endDate;      // 여행 종료일
    private List<String> preferences; // 선호하는 활동 또는 테마

    // Getter / Setter
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
}
