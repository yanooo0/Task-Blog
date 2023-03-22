package com.task.blog.shared.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NaverBlogDto {
    private List<ItemDto> items;
}
