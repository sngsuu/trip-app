package com.hwv1.tripapp.controller;

import com.hwv1.tripapp.dto.TripPlanRequest;
import com.hwv1.tripapp.dto.TripPlanResponse;
import com.hwv1.tripapp.service.TripPlannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 여행 플랜 API 컨트롤러
 */
@RestController
@RequestMapping("/api/trip")
public class TripController {

    private final TripPlannerService tripPlannerService;

    public TripController(TripPlannerService tripPlannerService) {
        this.tripPlannerService = tripPlannerService;
    }

    /**
     * 여행 계획 요청을 받아 AI를 통해 여행 일정을 생성하여 반환
     * @param request TripPlanRequest 사용자 입력
     * @return TripPlanResponse 생성된 여행 일정 결과
     */
    @PostMapping("/plan")
    public ResponseEntity<TripPlanResponse> planTrip(@RequestBody TripPlanRequest request) {
        TripPlanResponse response = tripPlannerService.planTrip(request);
        return ResponseEntity.ok(response);
    }
}
