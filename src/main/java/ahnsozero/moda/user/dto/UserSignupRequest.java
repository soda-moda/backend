package ahnsozero.moda.user.dto;

public record UserSignupRequest(
    String email,
    String name,
    String social
) {}