package com.tpokora.projects.articles.dao;

import com.tpokora.projects.articles.model.ListArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pokor on 05.06.2016.
 */
@Repository
public interface ListArticleRepository extends JpaRepository<ListArticle, Integer> {
}
