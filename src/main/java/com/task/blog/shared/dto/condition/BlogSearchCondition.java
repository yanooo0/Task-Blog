package com.task.blog.shared.dto.condition;

import com.task.blog.shared.base.SortTypes;
import com.task.blog.shared.base.SourceTypes;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogSearchCondition {
    @ApiModelProperty(value = "검색어", required = true)
    private String query;
    private Integer page = 1;
    private Integer size = 10;
    @ApiModelProperty(value = "검색소스: KAKAO(카카오), NAVER(네이버)")
    private SourceTypes sourceType = SourceTypes.KAKAO;
    @ApiModelProperty(value = "정렬조건: ACCURACY(정확도), RECENCY(최근순)")
    private SortTypes sortType = SortTypes.ACCURACY;
}
