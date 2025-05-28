package com.hwv1.tripapp.service.ai.mock;

import com.hwv1.tripapp.service.ai.RoutePlannerService;
import com.hwv1.tripapp.dto.TripPlanRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 동선 최적화의 Mock 구현
 */
@Service
public class MockRoutePlannerService implements RoutePlannerService {

    @Override
    public List<String> planRoute(List<String> places, TripPlanRequest request) {
        // 장소 그대로 하루 단위 일정으로 반환 (더미)
        return places;
    }
}
