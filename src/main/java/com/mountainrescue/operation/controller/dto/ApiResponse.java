package com.mountainrescue.operation.controller.dto;

public record ApiResponse<T>(
        String message,
        int status,
        T data
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", 200, data);
    }
}
