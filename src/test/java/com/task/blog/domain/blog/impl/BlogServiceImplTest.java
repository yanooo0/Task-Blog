package com.task.blog.domain.blog.impl;

import com.task.blog.delegate.external.KaKaoApiDelegate;
import com.task.blog.delegate.external.NaverApiDelegate;
import com.task.blog.domain.blog.BlogService;
import com.task.blog.shared.base.SortTypes;
import com.task.blog.shared.base.SourceTypes;
import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class BlogServiceImplTest {

    @Autowired
    BlogService blogService;

    @Test
    @DisplayName("카카오 api 요청 성공 테스트")
    void requestKakaoApi_success() {
        BlogSearchCondition blogSearchCondition = new BlogSearchCondition();
        blogSearchCondition.setPage(1);
        blogSearchCondition.setSize(20);
        blogSearchCondition.setQuery("맛집");
        blogSearchCondition.setSortType(SortTypes.ACCURACY);
        blogSearchCondition.setSourceType(SourceTypes.KAKAO);

        BlogDto blogDto = blogService.searchBlog(blogSearchCondition);
        Assertions.assertThat(blogDto.getPage()).isEqualTo(1);
    }

    @Test
    @DisplayName("네이버 api 요청 성공 테스트")
    void requestNaverApi_success() {
        BlogSearchCondition blogSearchCondition = new BlogSearchCondition();
        blogSearchCondition.setPage(1);
        blogSearchCondition.setSize(20);
        blogSearchCondition.setQuery("맛집");
        blogSearchCondition.setSortType(SortTypes.ACCURACY);
        blogSearchCondition.setSourceType(SourceTypes.NAVER);

        blogService.searchBlog(blogSearchCondition);
    }
}