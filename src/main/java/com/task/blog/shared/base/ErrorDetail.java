package com.task.blog.shared.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorDetail {
    private String errorDetail;
}
