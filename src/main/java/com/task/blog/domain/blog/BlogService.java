package com.task.blog.domain.blog;

import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;

public interface BlogService {
    BlogDto searchBlog(BlogSearchCondition blogSearchCondition);
}
