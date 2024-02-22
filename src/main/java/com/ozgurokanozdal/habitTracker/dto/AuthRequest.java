package com.ozgurokanozdal.habitTracker.dto;

public record AuthRequest(
        String username,
        String password
) {
}
