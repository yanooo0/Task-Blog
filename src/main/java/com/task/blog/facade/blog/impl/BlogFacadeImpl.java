package com.task.blog.facade.blog.impl;

import com.task.blog.domain.blog.BlogService;
import com.task.blog.domain.keyword.KeywordService;
import com.task.blog.facade.blog.BlogFacade;
import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogFacadeImpl implements BlogFacade {
    private final BlogService blogService;
    private final KeywordService keywordService;

    @Override
    public BlogDto searchBlog(BlogSearchCondition blogSearchCondition) {
        keywordService.increaseCountByKeyword(blogSearchCondition.getQuery());
        return blogService.searchBlog(blogSearchCondition);
    }
}
