package com.hwv1.tripapp.service.ai;

/**
 * 동선 최적화 서비스 인터페이스
 */
public interface RoutePlannerService {
    java.util.List<String> planRoute(java.util.List<String> places, com.hwv1.tripapp.dto.TripPlanRequest request);
}
