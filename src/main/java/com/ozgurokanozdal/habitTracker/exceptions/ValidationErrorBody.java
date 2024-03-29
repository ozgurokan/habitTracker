package com.ozgurokanozdal.habitTracker.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public record ValidationErrorBody(
        String path,
        int statusCode,
        Set<String> errors,
        LocalDateTime localDateTime
) {
}
