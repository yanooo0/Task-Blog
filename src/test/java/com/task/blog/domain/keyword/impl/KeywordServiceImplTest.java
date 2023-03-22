package com.task.blog.domain.keyword.impl;

import com.task.blog.datastore.entity.Keyword;
import com.task.blog.datastore.repository.KeywordRepository;
import com.task.blog.domain.keyword.KeywordService;
import com.task.blog.shared.dto.KeywordDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class KeywordServiceImplTest {
    @Autowired
    KeywordService keywordService;
    @Autowired
    KeywordRepository keywordRepository;

    @Test
    @DisplayName("처음 요청 시")
    void makeNewKeyword_success_WhenIsFirstRequest() {
        final String keywordName = "맛집";
        keywordService.increaseCountByKeyword(keywordName);

        Keyword keyword = keywordRepository.findByKeywordName(keywordName).get();

        Assertions.assertThat(keyword.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("두 번째 요청 시")
    void countKeyword_success_WhenIsSecondRequest() {
        final String keywordName = "맛집";
        keywordService.increaseCountByKeyword(keywordName);
        keywordService.increaseCountByKeyword(keywordName);

        Keyword keyword = keywordRepository.findByKeywordName(keywordName).get();

        Assertions.assertThat(keyword.getCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("많은 순서대로 내림차순 정렬 확인")
    void countKeyword_success_WhenMultipleRequest() {
        final String keywordName1 = "맛집";
        final String keywordName2 = "주유소";

        keywordService.increaseCountByKeyword(keywordName2);

        keywordService.increaseCountByKeyword(keywordName1);
        keywordService.increaseCountByKeyword(keywordName1);

        List<KeywordDto> top10Keyword = keywordService.getTop10Keyword();

        Assertions.assertThat(top10Keyword.get(0).getKeywordName()).isEqualTo(keywordName1);
    }
}