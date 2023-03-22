package com.task.blog.datastore.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "keyword", indexes = @Index(name = "idx__count", columnList = "count"))
public class Keyword {
    @Id
    @GeneratedValue
    private Long id;

    private String keywordName;

    private Long count;

    public void increaseCount() {
        this.count = this.count + 1;
    }

    public static Keyword createKeyword(String keywordName) {
        return Keyword.builder()
                .keywordName(keywordName)
                .count(0L)
                .build();
    }

    public Keyword(Long id, String keywordName, Long count) {
        this.id = id;
        this.keywordName = keywordName;
        this.count = count;
    }
}
