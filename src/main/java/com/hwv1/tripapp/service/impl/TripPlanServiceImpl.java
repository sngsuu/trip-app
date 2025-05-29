// ✅ 여러 AI 모델을 지원하는 여행 요약 서비스 구현체 (파일명: TripPlanServiceImpl.java)
package com.hwv1.tripapp.service.impl;

import com.hwv1.tripapp.dto.TripPlanRequest;
import com.hwv1.tripapp.dto.TripPlanResponse;
import com.hwv1.tripapp.service.TripPlanService;
import com.hwv1.tripapp.util.TripResultParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TripPlanServiceImpl implements TripPlanService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${groq.api-key}")
    private String groqApiKey;

    @Value("${chatgpt.api-key}")
    private String chatGptApiKey;

    @Override
    public TripPlanResponse generatePlan(TripPlanRequest request) {
        String model = request.getModel();
        String prompt = buildPrompt(request);

        // ✅ 공통 메시지 구조 (OpenAI / Groq / OpenRouter 호환)
        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("messages", List.of(
                Map.of("role", "system", "content", "당신은 여행 전문가입니다. 사용자의 입력을 바탕으로 상세한 여행 일정을 요약해주세요."),
                Map.of("role", "user", "content", prompt)
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String apiUrl;
        String apiKey;

        // ✅ 모델 이름 기준으로 분기
        if (model.startsWith("gpt-") || model.startsWith("mixtral") || model.contains("llama") || model.contains("gemma")) {
            apiUrl = "https://api.groq.com/openai/v1/chat/completions";
            apiKey = groqApiKey;
        } else {
            apiUrl = "https://openrouter.ai/api/v1/chat/completions";
            apiKey = chatGptApiKey;
        }

        headers.setBearerAuth(apiKey);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    apiUrl,
                    entity,
                    Map.class
            );

            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String content = (String) message.get("content");

            // ✅ 마크다운 텍스트 → TripPlanResponse 구조로 파싱
            return TripResultParser.parse(content);

        } catch (Exception e) {
            return new TripPlanResponse("[API 호출 오류] " + e.getMessage(), List.of(), List.of());
        }
    }

    // ✅ 사용자 입력 기반 프롬프트 생성
    private String buildPrompt(TripPlanRequest req) {
        return String.format("""
            목적지: %s
            여행 기간: %s ~ %s
            %s%s하루 이동 가능 거리: %dkm
            %s%s여행 일정 요약, 추천 장소, 활동, 음식, 예상 비용 등을 포함해 주세요.
            """,
                req.getDestination(),
                req.getStartDate(), req.getEndDate(),
                req.getCompanion() != null ? "동반자: " + req.getCompanion() + "\n" : "",
                req.getTransport() != null ? "이동 수단: " + req.getTransport() + "\n" : "",
                req.getDistance(),
                req.getDiet() != null ? "식사 취향: " + req.getDiet() + "\n" : "",
                req.getBudget() > 0 ? "일일 예산: " + req.getBudget() + "원\n" : ""
        );
    }
}