package com.hwv1.tripapp.util;

import com.hwv1.tripapp.dto.TripPlanResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class TripResultParser {

    public static TripPlanResponse parse(String content) {
        String summary = extractSummary(content);
        List<TripPlanResponse.DailyPlan> dailyPlans = extractDailyPlans(content);
        List<String> costs = extractCosts(content);

        return new TripPlanResponse(summary, dailyPlans, costs);
    }

    private static String extractSummary(String content) {
        int dayIndex = content.indexOf("#### ");
        if (dayIndex == -1) return content.trim();
        return content.substring(0, dayIndex).trim();
    }

    private static List<TripPlanResponse.DailyPlan> extractDailyPlans(String content) {
        List<TripPlanResponse.DailyPlan> dailyPlans = new ArrayList<>();

        Pattern dateBlockPattern = Pattern.compile(
                "####\\s*(\\d{4}-\\d{2}-\\d{2})\\s*\\(([^)]+)\\)\\s*\\n((?:-\\s.*\\n?)*)",
                Pattern.MULTILINE
        );

        Matcher matcher = dateBlockPattern.matcher(content);

        while (matcher.find()) {
            String date = matcher.group(1) + " (" + matcher.group(2) + ")";
            String activitiesRaw = matcher.group(3);

            List<String> activities = new ArrayList<>();
            for (String line : activitiesRaw.split("\\n")) {
                if (line.startsWith("- ")) {
                    activities.add(line.substring(2).trim());
                }
            }

            dailyPlans.add(new TripPlanResponse.DailyPlan(date, activities));
        }

        return dailyPlans;
    }

    private static List<String> extractCosts(String content) {
        List<String> costs = new ArrayList<>();

        int start = content.indexOf("### 예상 비용");
        if (start == -1) start = content.indexOf("총 예상 비용");
        if (start == -1) return costs;

        String costSection = content.substring(start);

        Pattern pattern = Pattern.compile("(?m)^[-*]+\\s*(.+)");
        Matcher matcher = pattern.matcher(costSection);

        while (matcher.find()) {
            costs.add(matcher.group(1).trim());
        }

        return costs;
    }
}