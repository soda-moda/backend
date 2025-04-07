package ahnsozero.moda.user.controller;

import ahnsozero.moda.user.service.UserService;
import ahnsozero.moda.user.dto.UserResponse;
import ahnsozero.moda.user.dto.UserSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Long signup(@RequestBody UserSignupRequest request) {
        return userService.signup(request);
    }

    @GetMapping("/{email}")
    public UserResponse getUser(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}