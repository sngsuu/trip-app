package com.hwv1.tripapp.service.ai;

/**
 * 여행 비용 계산기 인터페이스
 */
public interface CostEstimatorService {
    int estimate(java.util.List<String> places, com.hwv1.tripapp.dto.TripPlanRequest request);
}
