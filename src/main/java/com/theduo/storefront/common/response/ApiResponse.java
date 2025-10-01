package com.theduo.storefront.common.response;

import java.time.Instant;

public record ApiResponse<T>(
        Instant timestamp,
        int status,
        String message,
        T data,
        String path
) {
    public static <T> ApiResponse<T> success(T data, String message, int status, String path) {
        return new ApiResponse<>(Instant.now(), status, message, data, path);
    }

    public static <T> ApiResponse<T> error(String message, int status, String path) {
        return new ApiResponse<>(Instant.now(), status, message, null, path);
    }
}
