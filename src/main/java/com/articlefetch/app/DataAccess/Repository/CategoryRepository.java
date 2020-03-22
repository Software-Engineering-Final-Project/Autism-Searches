package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.Controller.JacksonModels.Category;
import com.articlefetch.app.DataAccess.ModelDomain.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * This class extends the JPA repository to inherit generic CRUD operations. This also
 *   allows Spring Data to create a Spring Bean.
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    @Query("SELECT s FROM CategoryEntity s WHERE category_name = ?1")
    List<CategoryEntity> findExistingConflicts(String article_name);
}
