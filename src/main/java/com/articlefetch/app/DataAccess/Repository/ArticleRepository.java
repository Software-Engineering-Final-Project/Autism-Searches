package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * This class extends the JPA repository to inherit generic CRUD operations. This also
 *   allows Spring Data to create a Spring Bean.
 */
@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Integer> {

    @Query("SELECT s FROM ArticleEntity s WHERE article_title = ?1 OR article_authors = ?2")
    List<ArticleEntity> findExistingConflicts(String article_title, String article_authors);

    @Query("SELECT s FROM ArticleEntity s WHERE article_title = ?1")
    Optional<ArticleEntity> findArticleByname(String article_title);
}
