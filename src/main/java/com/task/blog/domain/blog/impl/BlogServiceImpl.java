package com.task.blog.domain.blog.impl;

import com.task.blog.delegate.external.KaKaoApiDelegate;
import com.task.blog.delegate.external.NaverApiDelegate;
import com.task.blog.domain.blog.BlogService;
import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {
    private final KaKaoApiDelegate kaKaoApiDelegate;
    private final NaverApiDelegate naverApiDelegate;

    @Override
    public BlogDto searchBlog(BlogSearchCondition blogSearchCondition) {
        return switch (blogSearchCondition.getSourceType()) {
            case KAKAO -> kaKaoApiDelegate.searchBlog(blogSearchCondition);
            case NAVER -> naverApiDelegate.searchBlog(blogSearchCondition);
        };
    }
}
