package com.task.blog.shared.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KeywordDto {
    private Long id;
    private String keywordName;
    private Long count;
}
