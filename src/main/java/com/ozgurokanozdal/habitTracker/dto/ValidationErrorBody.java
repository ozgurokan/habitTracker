package com.ozgurokanozdal.habitTracker.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record ValidationErrorBody(
        String path,
        int statusCode,
        Set<String> errors,
        LocalDateTime localDateTime
) {
}
