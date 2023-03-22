package com.task.blog.delegate.external;

import com.task.blog.shared.base.SortTypes;
import com.task.blog.shared.base.SourceTypes;
import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;
import com.task.blog.shared.exception.InvalidRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaverApiDelegateTest {

    @Autowired
    NaverApiDelegate naverApiDelegate;

    @Test
    @DisplayName("정상적인 카카오 API 요청 시 성공")
    void requestNaverApi_Success_IfRequestIsValid() {
        BlogSearchCondition blogSearchCondition = new BlogSearchCondition();
        blogSearchCondition.setPage(1);
        blogSearchCondition.setSize(20);
        blogSearchCondition.setQuery("맛집");
        blogSearchCondition.setSortType(SortTypes.ACCURACY);
        blogSearchCondition.setSourceType(SourceTypes.NAVER);

        BlogDto blogDto = naverApiDelegate.searchBlog(blogSearchCondition);

        Assertions.assertThat(blogDto.getPage()).isEqualTo(1);
    }

    @Test()
    @DisplayName("사이즈가 100 값 이상 요청 시 실패")
    void requestNaverApi_InvalidRequestException_IfSizeIsInValid() {
        BlogSearchCondition blogSearchCondition = new BlogSearchCondition();
        blogSearchCondition.setPage(1);
        blogSearchCondition.setSize(101);
        blogSearchCondition.setQuery("맛집");
        blogSearchCondition.setSortType(SortTypes.ACCURACY);
        blogSearchCondition.setSourceType(SourceTypes.NAVER);

        Assertions.assertThatThrownBy(() -> naverApiDelegate.searchBlog(blogSearchCondition))
                .isInstanceOf(InvalidRequestException.class);
    }

    @Test()
    @DisplayName("곱이 1000 이상 요청 시 실패")
    void requestNaverApi_InvalidRequestException_IfPageIsInValid() {
        BlogSearchCondition blogSearchCondition = new BlogSearchCondition();
        blogSearchCondition.setPage(510);
        blogSearchCondition.setSize(10);
        blogSearchCondition.setQuery("맛집");
        blogSearchCondition.setSortType(SortTypes.ACCURACY);
        blogSearchCondition.setSourceType(SourceTypes.NAVER);

        Assertions.assertThatThrownBy(() -> naverApiDelegate.searchBlog(blogSearchCondition))
                .isInstanceOf(InvalidRequestException.class);
    }
}