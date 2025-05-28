package com.hwv1.tripapp.service.ai.mock;

import com.hwv1.tripapp.service.ai.CostEstimatorService;
import com.hwv1.tripapp.dto.TripPlanRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 여행 비용 계산기의 Mock 구현
 */
@Service
public class MockCostEstimatorService implements CostEstimatorService {

    @Override
    public int estimate(List<String> places, TripPlanRequest request) {
        // 장소 수 x 10만원 기준으로 단순 계산
        return places.size() * 100000;
    }
}
