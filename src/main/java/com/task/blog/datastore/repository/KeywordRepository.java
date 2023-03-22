package com.task.blog.datastore.repository;

import com.task.blog.datastore.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByKeywordName(String keywordName);

    List<Keyword> findTop10ByOrderByCountDesc();
}
