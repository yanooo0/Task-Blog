package com.task.blog.shared.exception;

public class ApiRequestUnauthorizedException extends RuntimeException {
    public ApiRequestUnauthorizedException(String message) {
        super(message);
    }
}
