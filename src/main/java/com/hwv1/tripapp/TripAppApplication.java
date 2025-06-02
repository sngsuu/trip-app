package com.hwv1.tripapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TripAppApplication {

    public static void main(String[] args) {
        // ✅ .env 로드 및 환경 변수로 등록
        Dotenv dotenv = Dotenv.configure()
                .directory("./")       // 루트 경로 (또는 "src/main/resources"로 변경 가능)
                .ignoreIfMissing()     // .env 없어도 실행되게
                .load();

        // 시스템 속성 등록 (Spring에서 인식 가능하도록)
        System.setProperty("GROQ_API_KEY", dotenv.get("GROQ_API_KEY"));
        System.setProperty("CHATGPT_API_KEY", dotenv.get("CHATGPT_API_KEY"));

        SpringApplication.run(TripAppApplication.class, args);
    }
}