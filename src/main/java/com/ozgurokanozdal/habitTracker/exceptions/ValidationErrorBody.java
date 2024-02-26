package com.ozgurokanozdal.habitTracker.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorBody(
        String path,
        int statusCode,
        Map<String,String> errors,
        LocalDateTime localDateTime
) {
}
