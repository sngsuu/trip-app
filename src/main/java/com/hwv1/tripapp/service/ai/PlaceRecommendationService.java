package com.hwv1.tripapp.service.ai;

/**
 * 명소 추천 서비스 인터페이스
 */
public interface PlaceRecommendationService {
    java.util.List<String> recommend(com.hwv1.tripapp.dto.TripPlanRequest request);
}
