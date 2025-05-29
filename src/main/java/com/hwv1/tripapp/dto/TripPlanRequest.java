// ✅ 여행 요약 요청용 DTO (파일명: TripPlanRequest.java)
package com.hwv1.tripapp.dto;

import lombok.Data;

@Data
public class TripPlanRequest {
    private String destination; // 목적지
    private String startDate;   // 여행 시작일
    private String endDate;     // 여행 종료일
    private String companion;   // 동반자 유형
    private String transport;   // 이동 수단
    private int distance;       // 하루 이동 가능 거리 (km)
    private String diet;        // 식사 취향
    private int budget;         // 일일 예산
    private String model;       // 사용할 AI 모델명 (프론트에서 전달)
}
