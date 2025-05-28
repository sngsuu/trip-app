package com.hwv1.tripapp.service.ai.mock;

import com.hwv1.tripapp.service.ai.PlaceRecommendationService;
import com.hwv1.tripapp.dto.TripPlanRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 명소 추천 서비스의 Mock 구현
 */
@Service
public class MockPlaceRecommendationService implements PlaceRecommendationService {

    @Override
    public List<String> recommend(TripPlanRequest request) {
        // 목적지에 따라 더미 명소 목록 반환
        return Arrays.asList("명소 1", "명소 2", "명소 3");
    }
}
