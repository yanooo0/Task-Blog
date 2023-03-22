package com.task.blog.shared.dto.condition;

import com.task.blog.shared.base.SortTypes;
import com.task.blog.shared.base.SourceTypes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogSearchCondition {
    private String query;
    private Integer page = 1;
    private Integer size = 10;
    private SourceTypes sourceType = SourceTypes.KAKAO;
    private SortTypes sortType = SortTypes.ACCURACY;
}
