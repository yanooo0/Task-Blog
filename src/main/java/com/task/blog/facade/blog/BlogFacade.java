package com.task.blog.facade.blog;

import com.task.blog.shared.dto.BlogDto;
import com.task.blog.shared.dto.condition.BlogSearchCondition;

public interface BlogFacade {
    BlogDto searchBlog(BlogSearchCondition blogSearchCondition);
}
