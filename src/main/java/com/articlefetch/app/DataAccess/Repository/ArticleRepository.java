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


    @Query("SELECT s FROM ArticleEntity s WHERE username = ?1 OR email = ?2")
    List<ArticleEntity> findExistingConflicts(String username, String password);

    Optional<ArticleEntity> findByArticlename(String article_name);
}
