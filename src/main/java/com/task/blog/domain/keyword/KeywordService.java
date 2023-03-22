package com.task.blog.domain.keyword;

import com.task.blog.shared.dto.KeywordDto;

import java.util.List;

public interface KeywordService {
    void increaseCountByKeyword(String keyword);

    List<KeywordDto> getTop10Keyword();
}
