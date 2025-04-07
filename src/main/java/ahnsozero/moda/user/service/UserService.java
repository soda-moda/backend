package ahnsozero.moda.user.service;

import ahnsozero.moda.user.dto.UserResponse;
import ahnsozero.moda.user.dto.UserSignupRequest;
import ahnsozero.moda.user.entity.SocialType;
import ahnsozero.moda.user.entity.User;
import ahnsozero.moda.user.entity.UserStatus;
import ahnsozero.moda.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //모든 final 필드에 대해 생성자 자동 생성

public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long signup(UserSignupRequest request) {
        User user = User.builder()
                        .email(request.email())
                        .name(request.name())
                        .social(SocialType.valueOf(request.social()
                                                          .toUpperCase()))
                        .status(UserStatus.ACTIVE)
                        .build();
        return userRepository.save(user)
                             .getUserId();
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                                  .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return new UserResponse(
            user.getUserId(),
            user.getEmail(),
            user.getName(),
            user.getSocial()
                .name(),
            user.getStatus()
                .name()
        );
    }
}