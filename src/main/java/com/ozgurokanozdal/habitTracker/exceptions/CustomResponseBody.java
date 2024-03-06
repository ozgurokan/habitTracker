package com.ozgurokanozdal.habitTracker.exceptions;

import java.time.LocalDateTime;

public record CustomResponseBody(
        String path,
        int statusCode,
        String errors,
        LocalDateTime localDateTime
) {
}
