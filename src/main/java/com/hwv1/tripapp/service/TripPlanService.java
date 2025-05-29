// ✅ 여행 요약 생성 서비스 인터페이스 (TripPlanService.java)
package com.hwv1.tripapp.service;

import com.hwv1.tripapp.dto.TripPlanRequest;
import com.hwv1.tripapp.dto.TripPlanResponse;

public interface TripPlanService {

    /**
     * 사용자 요청 정보를 기반으로 AI에게 여행 요약 생성 요청
     * @param request 사용자 요청 정보 (여행 조건 + 모델명)
     * @return 요약된 여행 일정 텍스트
     */
    TripPlanResponse generatePlan(TripPlanRequest request);
}
