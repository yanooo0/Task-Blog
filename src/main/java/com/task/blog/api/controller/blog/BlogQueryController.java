package com.task.blog.api.controller.blog;

import com.task.blog.facade.blog.BlogFacade;
import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogQueryController {
    private final BlogFacade blogFacade;

    @GetMapping("/api/v1/blog")
    public ResponseEntity<BlogDto> searchBlog(BlogSearchCondition blogSearchCondition) {
        return ResponseEntity.ok(blogFacade.searchBlog(blogSearchCondition));
    }
}
