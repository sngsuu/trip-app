// ✅ 여행 요약 생성 컨트롤러 (파일명: TripController.java)
package com.hwv1.tripapp.controller;

import com.hwv1.tripapp.dto.TripPlanRequest;
import com.hwv1.tripapp.dto.TripPlanResponse;
import com.hwv1.tripapp.service.TripPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripPlanService tripPlanService;

    /**
     * 여행 계획 생성 요청을 처리하는 POST 엔드포인트
     * @param request 사용자의 여행 조건 및 모델 정보
     * @return AI가 생성한 여행 요약 응답
     */
    @PostMapping("/plan")
    public TripPlanResponse generatePlan(@RequestBody TripPlanRequest request) {
        return tripPlanService.generatePlan(request);
    }
}
