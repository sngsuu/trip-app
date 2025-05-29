// ✅ 목업 응답 구현체 (TripPlanMockServiceImpl.java)
package com.hwv1.tripapp.service.mock;

import com.hwv1.tripapp.dto.TripPlanRequest;
import com.hwv1.tripapp.dto.TripPlanResponse;
import com.hwv1.tripapp.service.TripPlanService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("mock")
public class TripPlanMockServiceImpl implements TripPlanService {

    @Override
    public TripPlanResponse generatePlan(TripPlanRequest request) {
        // ✅ 요약
        String summary = String.format("[MOCK 응답]\n%s에서 %s ~ %s까지 여행!\n맛집 탐방 + 관광 추천 일정입니다.",
                request.getDestination(), request.getStartDate(), request.getEndDate());

        // ✅ 일별 일정
        List<TripPlanResponse.DailyPlan> dailyPlan = List.of(
                new TripPlanResponse.DailyPlan("6월 1일", List.of("오전: 호텔 체크인 및 휴식", "오후: 도심 탐방")),
                new TripPlanResponse.DailyPlan("6월 2일", List.of("오전: 관광지 A 방문", "오후: 지역 시장 투어"))
        );

        // ✅ 비용 정보
        List<String> costs = List.of("숙박: 10만 원", "식비: 5만 원", "교통비: 3만 원");

        return new TripPlanResponse(summary, dailyPlan, costs);
    }
}