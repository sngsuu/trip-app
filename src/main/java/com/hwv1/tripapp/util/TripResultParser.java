// ✅ AI 응답 결과 파싱 유틸 클래스 (파일명: TripResultParser.java)
package com.hwv1.tripapp.util;

import com.hwv1.tripapp.dto.TripPlanResponse;
import java.util.*;
import java.util.regex.*;

public class TripResultParser {

    // ✅ 메인 파싱 메서드
    public static TripPlanResponse parse(String rawText) {
        String summary = extractSummary(rawText);
        List<TripPlanResponse.DailyPlan> dailyPlan = extractDailyPlans(rawText);
        List<String> costs = extractCosts(rawText);

        return new TripPlanResponse(summary, dailyPlan, costs);
    }

    // ✅ 여행 개요 파싱 (맨 앞 부분)
    private static String extractSummary(String text) {
        int scheduleIndex = text.indexOf("#### 20");
        if (scheduleIndex == -1) scheduleIndex = text.indexOf("### ");
        return (scheduleIndex != -1) ? text.substring(0, scheduleIndex).trim() : text.trim();
    }

    // ✅ 일별 일정 파싱
    private static List<TripPlanResponse.DailyPlan> extractDailyPlans(String text) {
        List<TripPlanResponse.DailyPlan> days = new ArrayList<>();

        // 날짜 섹션 찾기 (예: "#### 2025년 5월 28일")
        Pattern dayPattern = Pattern.compile("#### (\\d{4}년 \\d{1,2}월 \\d{1,2}일.*?)\\n");
        Matcher matcher = dayPattern.matcher(text);

        List<Integer> indices = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        while (matcher.find()) {
            indices.add(matcher.start());
            headers.add(matcher.group(1));
        }
        indices.add(text.length()); // 마지막

        for (int i = 0; i < headers.size(); i++) {
            String date = headers.get(i);
            String section = text.substring(indices.get(i), indices.get(i + 1));
            List<String> items = new ArrayList<>();

            // 활동 항목 추출 (예: 1. 또는 - 또는 시간대 포함)
            Pattern itemPattern = Pattern.compile("(?m)^[-\\d]+\\. .*|^\\* .*|^\\*\\*.*?\\*\\*: .*|^\\d{1,2}:\\d{2}.*");
            Matcher itemMatcher = itemPattern.matcher(section);
            while (itemMatcher.find()) {
                items.add(itemMatcher.group().trim());
            }
            days.add(new TripPlanResponse.DailyPlan(date, items));
        }

        return days;
    }

    // ✅ 비용 항목 파싱 ("예상 비용" 이후 섹션)
    private static List<String> extractCosts(String text) {
        List<String> costs = new ArrayList<>();
        int costIndex = text.indexOf("#### 여행 비용 예상");
        if (costIndex == -1) costIndex = text.indexOf("### 여행 비용 예상");
        if (costIndex == -1) return costs;

        String costSection = text.substring(costIndex);
        Pattern costPattern = Pattern.compile("(?m)^[-\\*] .*|^\\*\\*.*?:.*");
        Matcher matcher = costPattern.matcher(costSection);
        while (matcher.find()) {
            costs.add(matcher.group().trim());
        }
        return costs;
    }
}
