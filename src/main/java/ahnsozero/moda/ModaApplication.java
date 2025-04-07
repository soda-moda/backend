package ahnsozero.moda;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@EnableJpaAuditing // AuditingEntityListener를 사용가능하게 해줌
@SpringBootApplication
public class ModaApplication {

    public static void main(String[] args) {
        // .env 환경설정 불러오기
        Dotenv dotenv = Dotenv.configure()
                              .ignoreIfMissing() // .env 파일이 없어도 어플리케이션 죽지 않게 무시해주는 옵션
                              .load();
        // DB설정
        System.setProperty("DB_USER",dotenv.get("DB_USER"));
        System.setProperty("KAKAO_REST_API_KEY",dotenv.get("KAKAO_REST_API_KEY"));
        System.setProperty("KAKAO_SECRET",dotenv.get("KAKAO_SECRET"));
        System.setProperty("NAVER_CLIENT_ID",dotenv.get("NAVER_CLIENT_ID"));
        System.setProperty("NAVER_CLIENT_SECRET",dotenv.get("NAVER_CLIENT_SECRET"));
        System.setProperty("GOOGLE_CLIENT_ID",dotenv.get("GOOGLE_CLIENT_ID"));
        System.setProperty("GOOGLE_SECRET",dotenv.get("GOOGLE_SECRET"));

        SpringApplication.run(ModaApplication.class, args);
    }

}
