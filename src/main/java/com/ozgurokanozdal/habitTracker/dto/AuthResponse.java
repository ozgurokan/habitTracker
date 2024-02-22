package com.ozgurokanozdal.habitTracker.dto;


public record AuthResponse(
        String username,
        String token,
        long id
)
{
}
