package com.task.blog.api.advice;

import com.task.blog.shared.base.ErrorDetail;
import com.task.blog.shared.exception.ApiRequestUnauthorizedException;
import com.task.blog.shared.exception.InvalidRequestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalCustomExceptionHandler {

    @ExceptionHandler(ApiRequestUnauthorizedException.class)
    public ResponseEntity onApiRequestUnauthorizedException(ApiRequestUnauthorizedException exception) {
        log.error("api key error");
        ErrorDetail errorDetail = ErrorDetail.builder()
                .errorDetail(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity onInvalidRequestException(InvalidRequestException exception) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .errorDetail(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
    }

}
