package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountStatus;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.GenericDeclaration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is responsible for interfacing Hibernate data retrieval API for AccountEntity
 */
@Service
public class AccountServiceImpl implements AccountService, Conversion<AccountEntity, Account> {

    @Autowired AccountRepository accountRepository;

    @Override
    public void createAccount(Account account) throws DuplicateEntryException {
        // Check if an account exists
        if(!accountRepository.findExistingConflicts(account.username, account.password).isEmpty()) {
            throw new DuplicateEntryException();
        }
        accountRepository.save(convertToDAO(account));
    }

    @Override
    public Account getAccount(Integer account_id) throws AccountNotFoundException {
         return convertToJackson( accountRepository.findById(account_id)
                 .orElseThrow(() -> new AccountNotFoundException(account_id)));
    }


    @Override
    public List<Account> getAllAccounts() {
       List<AccountEntity> list = (List<AccountEntity>) accountRepository.findAll();
       Stream<Account> stream = list.stream().map( (account) -> convertToJackson(account));
       return stream.collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deactivateAccount(Integer id) throws AccountNotFoundException {
        accountRepository.setAccountStatus(0, id).orElseThrow(
                () -> new AccountNotFoundException(id));
    }

    @Transactional
    @Override
    public void reactivateAccount(Integer id) throws AccountNotFoundException {
        accountRepository.setAccountStatus(1, id).orElseThrow(
                () -> new AccountNotFoundException(id));
    }

    @Override
    public Account updateAccount(Integer id, Account account) throws AccountNotFoundException {
        AccountEntity entity = accountRepository.findById(id).orElseThrow( () -> new AccountNotFoundException(id));
        entity.setUsername(account.username);
        entity.setPassword(account.password);
        entity.setEmail(account.email);
        entity.setStatus(entity.getStatus()); // We don' allow status updates here
        entity.setPath(account.path);
        entity.setLast_name(account.last_name);
        entity.setFirst_name(account.first_name);
        accountRepository.save(entity);

        return convertToJackson(entity);
    }

    @Override
    public AccountEntity convertToDAO(Account obj) {
        AccountEntity entity = new AccountEntity();
        entity.setUsername(obj.username);
        entity.setPassword(obj.password);
        entity.setFirst_name(obj.first_name);
        entity.setLast_name(obj.last_name);
        entity.setStatus(obj.status);
        entity.setEmail(obj.email);
        entity.setPath(obj.path);
        entity.setId_account(obj.id);
        return entity;
    }

    @Override
    public Account convertToJackson(AccountEntity obj) {
        return new Account(obj.getUsername(), obj.getPassword(), obj.getFirst_name(), obj.getLast_name(),
                obj.getEmail(),obj.getAccount_id(), obj.getPath(), obj.getStatus());
    }
}
