package com.theduo.storefront.common.response;

import java.time.Instant;

public record SuccessResponse<T>(
        Instant timestamp,
        int status,
        String message,
        T data,
        String path
) {
    public static <T> SuccessResponse<T> of(T data, String message, int status, String path) {
        return new SuccessResponse<>(Instant.now(), status, message, data, path);
    }
}