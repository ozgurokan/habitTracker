package com.ozgurokanozdal.habitTracker.dto;

public record LikeCreateRequest(
        Long habitId,
        Long userId
) {
}
