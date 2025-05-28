package com.hwv1.tripapp.service.ai.mock;

import com.hwv1.tripapp.service.ai.SummaryGeneratorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 여행 요약 생성기의 Mock 구현
 */
@Service
public class MockSummaryGeneratorService implements SummaryGeneratorService {

    @Override
    public String generate(List<String> dailyPlan, int cost) {
        return String.format("총 %d개의 명소를 포함한 여행입니다. 예상 비용은 약 %,d원입니다.", dailyPlan.size(), cost);
    }
}
