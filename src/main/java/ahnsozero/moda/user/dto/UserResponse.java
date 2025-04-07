package ahnsozero.moda.user.dto;

public record UserResponse(
    Long userId,
    String email,
    String name,
    String social,
    String status
) {}