package com.hwv1.tripapp.service.ai;

/**
 * 여행 요약 생성기 인터페이스
 */
public interface SummaryGeneratorService {
    String generate(java.util.List<String> dailyPlan, int cost);
}
