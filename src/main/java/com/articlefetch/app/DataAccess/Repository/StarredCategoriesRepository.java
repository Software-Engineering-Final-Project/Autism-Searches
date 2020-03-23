package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.DataAccess.ModelDomain.StarredCategoriesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * This class extends the JPA repository to inherit generic CRUD operations. This also
 *   allows Spring Data to create a Spring Bean.
 */
@Repository
public interface StarredCategoriesRepository extends CrudRepository<StarredCategoriesEntity, Integer> {

    @Query("SELECT s FROM StarredCategoriesEntity s WHERE category_name = ?1")
    List<StarredCategoriesEntity> findExistingConflicts(String name);
}
