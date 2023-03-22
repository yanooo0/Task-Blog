package com.task.blog.delegate.external;

import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;
import com.task.blog.shared.exception.InvalidRequestException;
import com.task.blog.shared.exception.ApiRequestUnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class KaKaoApiDelegate implements ExternalApiDelegate {

    private final String KAKAO_ENDPOINT = "https://dapi.kakao.com/v2/search/blog";
    private final RestTemplate restTemplate;

    @Value("${kakao_apikey}")
    private String apiKey;

    @Override
    public BlogDto searchBlog(BlogSearchCondition blogSearchCondition) {
        validateBlogSearchCondition(blogSearchCondition);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("KakaoAK %s", apiKey));

        URI uri = UriComponentsBuilder.fromHttpUrl(KAKAO_ENDPOINT)
                .queryParam("query", blogSearchCondition.getQuery())
                .queryParam("page", blogSearchCondition.getPage())
                .queryParam("size", blogSearchCondition.getSize())
                .queryParam("sort", blogSearchCondition.getSortType().getKakao())
                .build()
                .toUri();
        System.out.println("uri = " + uri);
        System.out.println("apiKey = " + apiKey);

        try {
            ResponseEntity<BlogDto> response = restTemplate.exchange(uri.toString(), HttpMethod.GET, new HttpEntity<>(headers), BlogDto.class);
            BlogDto blogDto = Objects.requireNonNull(response.getBody());
            return createPage(blogSearchCondition, blogDto);
        } catch (HttpClientErrorException e) {
            HttpStatus statusCode = e.getStatusCode();
            if (statusCode == HttpStatus.UNAUTHORIZED) {
                throw new ApiRequestUnauthorizedException("인증 값에 문제가 발생했습니다.");
            }
        }
        throw new InvalidRequestException("잘못된 요청입니다");
    }

    private void validateBlogSearchCondition(BlogSearchCondition blogSearchCondition) {
        if (blogSearchCondition.getPage() > 50 || blogSearchCondition.getPage() < 1) {
            throw new InvalidRequestException("페이지는 1 - 50 사이의 값 이어야합니다");
        }
        if (blogSearchCondition.getSize() > 50 || blogSearchCondition.getSize() < 1) {
            throw new InvalidRequestException("사이즈는 1 - 50 사이의 값 이어야합니다");
        }
    }

    private BlogDto createPage(BlogSearchCondition blogSearchCondition, BlogDto blogDto) {
        blogDto.setPage(blogSearchCondition.getPage());
        blogDto.setSize(blogSearchCondition.getSize());
        return blogDto;
    }

}
