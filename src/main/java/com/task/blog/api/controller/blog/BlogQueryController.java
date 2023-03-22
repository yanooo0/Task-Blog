package com.task.blog.api.controller.blog;

import com.task.blog.facade.blog.BlogFacade;
import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogQueryController {
    private final BlogFacade blogFacade;

    @Operation(
            summary = "블로그 검색 API",
            description = "블로그를 검색 합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "잘못된 요청 시(예시: 검색어 미입력)")
    })
    @GetMapping("/api/v1/blog")
    public ResponseEntity<BlogDto> searchBlog(BlogSearchCondition blogSearchCondition) {
        return ResponseEntity.ok(blogFacade.searchBlog(blogSearchCondition));
    }
}
