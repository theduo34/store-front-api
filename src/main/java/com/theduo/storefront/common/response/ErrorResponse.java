package com.theduo.storefront.common.response;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String path
) {
    public static ErrorResponse of(String error, int status, String path) {
        return new ErrorResponse(Instant.now(), status, error, path);
    }
}