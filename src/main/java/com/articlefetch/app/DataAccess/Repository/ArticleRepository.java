package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * This class extends the JPA repository to inherit generic CRUD operations. This also
 *   allows Spring Data to create a Spring Bean.
 */
@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Integer> {

    @Query("SELECT s FROM ArticleEntity s WHERE article_name = ?1 OR article_site = ?2")
    List<ArticleEntity> findExistingConflicts(String article_name, String article_site);
}
