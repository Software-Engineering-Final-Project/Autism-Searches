package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * This class extends the JPA repository to inherit generic CRUD operations. This also
 *   allows Spring Data to create a Spring Bean.
 */
@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Integer> {

}
