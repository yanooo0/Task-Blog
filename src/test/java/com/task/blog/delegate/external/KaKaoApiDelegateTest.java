package com.task.blog.delegate.external;

import com.task.blog.shared.base.SortTypes;
import com.task.blog.shared.base.SourceTypes;
import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;
import com.task.blog.shared.exception.InvalidRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class KaKaoApiDelegateTest {

    @Autowired
    KaKaoApiDelegate kaKaoApiDelegate;

    @Test
    @DisplayName("정상적인 카카오 API 요청 시 성공")
    void requestKakaoApi_Success_IfRequestIsValid() {
        BlogSearchCondition blogSearchCondition = new BlogSearchCondition();
        blogSearchCondition.setPage(1);
        blogSearchCondition.setSize(20);
        blogSearchCondition.setQuery("맛집");
        blogSearchCondition.setSortType(SortTypes.ACCURACY);
        blogSearchCondition.setSourceType(SourceTypes.KAKAO);

        BlogDto blogDto = kaKaoApiDelegate.searchBlog(blogSearchCondition);

        Assertions.assertThat(blogDto.getPage()).isEqualTo(1);
    }

    @Test()
    @DisplayName("사이즈가 51 값 이상 요청 시 실패")
    void requestKakaoApi_InvalidRequestException_IfSizeIsInValid() {
        BlogSearchCondition blogSearchCondition = new BlogSearchCondition();
        blogSearchCondition.setPage(1);
        blogSearchCondition.setSize(51);
        blogSearchCondition.setQuery("맛집");
        blogSearchCondition.setSortType(SortTypes.ACCURACY);
        blogSearchCondition.setSourceType(SourceTypes.KAKAO);

        Assertions.assertThatThrownBy(() -> kaKaoApiDelegate.searchBlog(blogSearchCondition))
                .isInstanceOf(InvalidRequestException.class);
    }

    @Test()
    @DisplayName("페이지가 51 값 이상 요청 시 실패")
    void requestKakaoApi_InvalidRequestException_IfPageIsInValid() {
        BlogSearchCondition blogSearchCondition = new BlogSearchCondition();
        blogSearchCondition.setPage(51);
        blogSearchCondition.setSize(10);
        blogSearchCondition.setQuery("맛집");
        blogSearchCondition.setSortType(SortTypes.ACCURACY);
        blogSearchCondition.setSourceType(SourceTypes.KAKAO);

        Assertions.assertThatThrownBy(() -> kaKaoApiDelegate.searchBlog(blogSearchCondition))
                .isInstanceOf(InvalidRequestException.class);
    }

}