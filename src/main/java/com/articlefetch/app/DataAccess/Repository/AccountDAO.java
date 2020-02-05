package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class extends the JPA repository to inherit generic CRUD operations. This also
 *   allows Spring Data to create a Spring Bean.
 */
@Repository
public interface AccountDAO extends JpaRepository<AccountEntity, Integer> {
}
