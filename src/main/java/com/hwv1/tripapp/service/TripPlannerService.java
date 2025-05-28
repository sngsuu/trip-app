package com.hwv1.tripapp.service;

import com.hwv1.tripapp.dto.TripPlanRequest;
import com.hwv1.tripapp.dto.TripPlanResponse;
import com.hwv1.tripapp.service.ai.CostEstimatorService;
import com.hwv1.tripapp.service.ai.PlaceRecommendationService;
import com.hwv1.tripapp.service.ai.RoutePlannerService;
import com.hwv1.tripapp.service.ai.SummaryGeneratorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 여행 계획 전체 흐름을 조율하는 메인 서비스 클래스
 * 각 AI 기능별 모듈을 주입받아 처리한다.
 */
@Service
public class TripPlannerService {

    private final PlaceRecommendationService placeRecommendationService;
    private final RoutePlannerService routePlannerService;
    private final CostEstimatorService costEstimatorService;
    private final SummaryGeneratorService summaryGeneratorService;

    public TripPlannerService(
            PlaceRecommendationService placeRecommendationService,
            RoutePlannerService routePlannerService,
            CostEstimatorService costEstimatorService,
            SummaryGeneratorService summaryGeneratorService
    ) {
        this.placeRecommendationService = placeRecommendationService;
        this.routePlannerService = routePlannerService;
        this.costEstimatorService = costEstimatorService;
        this.summaryGeneratorService = summaryGeneratorService;
    }

    /**
     * 여행 요청을 받아 각 기능별로 계획을 구성한 뒤 응답 객체에 담아 반환
     */
    public TripPlanResponse planTrip(TripPlanRequest request) {
        List<String> places = placeRecommendationService.recommend(request);
        List<String> route = routePlannerService.planRoute(places, request);
        int cost = costEstimatorService.estimate(places, request);
        String summary = summaryGeneratorService.generate(route, cost);

        TripPlanResponse response = new TripPlanResponse();
        response.setDailyPlan(route);
        response.setEstimatedCost(cost);
        response.setSummary(summary);

        return response;
    }
}
