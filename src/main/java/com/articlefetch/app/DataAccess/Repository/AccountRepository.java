package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * This class extends the JPA repository to inherit generic CRUD operations. This also
 *   allows Spring Data to create a Spring Bean.
 */
@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {

    @Query("SELECT s FROM AccountEntity s WHERE username = ?1 OR email = ?2")
    List<AccountEntity> findExistingConflicts(String username, String password);

    @Query("SELECT s FROM AccountEntity s WHERE username = ?1")
    Optional<AccountEntity> findAccountByUserName(String username);

    @Modifying
    @Transactional(timeout = 9)
    @Query(value = "UPDATE Account SET status = ? WHERE account_id = ?", nativeQuery = true)
    Optional<Integer> setAccountStatus(Integer status, Integer id);

}
