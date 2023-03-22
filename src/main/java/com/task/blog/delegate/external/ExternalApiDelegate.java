package com.task.blog.delegate.external;

import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;

public interface ExternalApiDelegate {
    BlogDto searchBlog(BlogSearchCondition blogSearchCondition);
}
