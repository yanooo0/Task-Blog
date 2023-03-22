package com.task.blog.domain.keyword.impl;

import com.task.blog.datastore.entity.Keyword;
import com.task.blog.datastore.repository.KeywordRepository;
import com.task.blog.domain.keyword.KeywordService;
import com.task.blog.shared.dto.KeywordDto;
import com.task.blog.shared.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordServiceImpl implements KeywordService {
    private final KeywordRepository keywordRepository;

    @Transactional
    @Override
    public void increaseCountByKeyword(String keywordName) {
        Keyword keyword = keywordRepository.findByKeywordName(keywordName)
                .orElseGet(() ->
                        keywordRepository.save(Keyword.createKeyword(keywordName)));

        keyword.increaseCount();
    }

    @Transactional(readOnly = true)
    @Override
    public List<KeywordDto> getTop10Keyword() {
        return ConvertUtil.convertList(keywordRepository.findTop10ByOrderByCountDesc(), KeywordDto.class);
    }
}
