package com.task.blog.shared.base;

import lombok.Getter;

@Getter
public enum SortTypes {
    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date");

    private String kakao;
    private String naver;

    SortTypes(String kakao, String naver) {
        this.kakao = kakao;
        this.naver = naver;
    }

}
