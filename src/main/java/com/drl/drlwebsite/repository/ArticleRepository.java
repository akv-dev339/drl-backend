package com.drl.drlwebsite.repository;

import com.drl.drlwebsite.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByVolumeId(Long volumeId);
}
