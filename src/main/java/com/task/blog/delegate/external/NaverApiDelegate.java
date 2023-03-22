package com.task.blog.delegate.external;

import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.DocumentDto;
import com.task.blog.shared.dto.NaverBlogDto;
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
public class NaverApiDelegate implements ExternalApiDelegate {
    private final String NAVER_ENDPOINT = "https://openapi.naver.com/v1/search/blog.json";
    private final RestTemplate restTemplate;

    @Value("${naver.clinet_id}")
    private String clientId;
    @Value("${naver.client_secret}")
    private String clientSecret;

    @Override
    public BlogDto searchBlog(BlogSearchCondition blogSearchCondition) {
        validateBlogSearchCondition(blogSearchCondition);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        URI uri = UriComponentsBuilder.fromHttpUrl(NAVER_ENDPOINT)
                .queryParam("query", blogSearchCondition.getQuery())
                .queryParam("start", blogSearchCondition.getPage() * blogSearchCondition.getSize())
                .queryParam("display", blogSearchCondition.getSize())
                .queryParam("sort", blogSearchCondition.getSortType().getNaver())
                .build()
                .toUri();

        try {
            ResponseEntity<NaverBlogDto> response = restTemplate.exchange(uri.toString(), HttpMethod.GET, new HttpEntity<>(headers), NaverBlogDto.class);

            return getBlogDto(Objects.requireNonNull(response.getBody()), blogSearchCondition);
        } catch (HttpClientErrorException e) {
            HttpStatus statusCode = e.getStatusCode();
            if (statusCode == HttpStatus.UNAUTHORIZED) {
                throw new ApiRequestUnauthorizedException("인증 값에 문제가 발생했습니다.");
            }
        }
        throw new InvalidRequestException("잘못된 요청입니다.");
    }

    private BlogDto getBlogDto(NaverBlogDto response, BlogSearchCondition blogSearchCondition) {
        return BlogDto.builder()
                .documents(DocumentDto.createDocuments(response.getItems()))
                .page(blogSearchCondition.getPage())
                .size(blogSearchCondition.getSize())
                .build();
    }

    private void validateBlogSearchCondition(BlogSearchCondition blogSearchCondition) {
        if (blogSearchCondition.getPage() * blogSearchCondition.getSize() > 1000) {
            throw new InvalidRequestException("페이지와 사이즈의 곱은 1000을 넘을 수 없습니다.");
        }
        if (blogSearchCondition.getSize() > 100 || blogSearchCondition.getSize() < 1) {
            throw new InvalidRequestException("사이즈는 1 - 100 사이의 값 이어야합니다");
        }
    }
}
