package com.ozgurokanozdal.habitTracker.dto;



public record CommentCreateRequest(
        String commentText,
        Long userId,
         Long habitId
) {
}
