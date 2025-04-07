package ahnsozero.moda.config;

import ahnsozero.moda.config.security.CustomOAuth2UserService;
import ahnsozero.moda.config.security.JwtTokenProvider;
import ahnsozero.moda.config.security.OAuth2LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

// Spring Security 전체 설정

@Configuration
@EnableWebSecurity // security를 활성화하는 어노테이션
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/oauth2/**").permitAll()
                .anyRequest().authenticated())
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
                .userInfoEndpoint(user -> user.userService(oAuth2UserService()))
                .successHandler(successHandler()));

        return http.build();
    }

    @Bean
    public CustomOAuth2UserService oAuth2UserService() {
        return new CustomOAuth2UserService();
    }

    @Bean
    public OAuth2LoginSuccessHandler successHandler() {
        return new OAuth2LoginSuccessHandler(jwtTokenProvider());
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider("modamoda", 1000 * 60 * 60); // 1시간
    }

}
