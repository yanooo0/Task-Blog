package com.task.blog.api.controller.keyword;

import com.task.blog.domain.keyword.KeywordService;
import com.task.blog.shared.dto.KeywordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KeywordQueryController {
    private final KeywordService keywordService;

    @GetMapping("/api/v1/popular-keyword")
    public ResponseEntity<List<KeywordDto>> getTop10Keyword() {
        return ResponseEntity.ok(keywordService.getTop10Keyword());
    }
}
