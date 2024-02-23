package com.ozgurokanozdal.habitTracker.exceptions;

import java.time.LocalDateTime;
import java.util.Set;

public record CustomResponseBody(
        String path,
        int statusCode,
        String message,
        LocalDateTime localDateTime
) {
}
