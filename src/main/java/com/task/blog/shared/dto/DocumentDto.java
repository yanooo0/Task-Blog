package com.task.blog.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DocumentDto {
    private String blogname;
    private String contents;
    private ZonedDateTime datetime;
    private String thumbnail;
    private String title;
    private String url;

    public static List<DocumentDto> createDocuments(List<ItemDto> items) {
        ArrayList<DocumentDto> documentDtos = new ArrayList<>();
        for (ItemDto item : items) {
            documentDtos.add(
                    DocumentDto.builder()
                            .blogname(item.getBloggername())
                            .contents(item.getDescription())
                            .url(item.getBloggerlink())
                            .datetime(LocalDate.parse(item.getPostdate(), DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay(ZoneId.systemDefault()))
                            .build()
            );
        }
        return documentDtos;
    }
}
