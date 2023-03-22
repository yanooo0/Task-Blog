package com.task.blog.api.controller.keyword;

import com.task.blog.domain.keyword.KeywordService;
import com.task.blog.shared.dto.KeywordDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KeywordQueryController {
    private final KeywordService keywordService;

    @Operation(
            summary = "인기 키워드 10개 출력 API",
            description = "인기 키워드 10개를 출력합니다."
    )
    @GetMapping("/api/v1/popular-keyword")
    public ResponseEntity<List<KeywordDto>> getTop10Keyword() {
        return ResponseEntity.ok(keywordService.getTop10Keyword());
    }
}
