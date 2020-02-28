package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Common.T;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountStatus;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is responsible for interfacing Hibernate data retrieval API for AccountEntity
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired AccountRepository accountRepository;

    @Override
    public AccountStatus createAccount(Account account) {
        try {
            findExistingAccountConflicts(account.username, account.password);
            accountRepository.save(createNewAccountEntry(account));
            return new AccountStatus().accountQuerySuccess();
        } catch (HibernateException e){
            return new AccountStatus().accountFailed(e);
        }
    }

    @Override
    public Account getAccount(Integer account_id) {
        try {
            AccountEntity account =  accountRepository.findById(account_id).orElseThrow(
                    () -> new AccountNotFoundException("No account exists with the given id")
            );
            return toJacksonAccountObject(account);
        } catch (Exception e){
            //TODO: HANDEL ERROR
            return new Account();
        }
    }

    @Override
    public List<Account> getAllAccounts() {
       List<AccountEntity> list = (List<AccountEntity>) accountRepository.findAll();
       Stream<Account> stream = list.stream().map( (account) -> toJacksonAccountObject(account));
       return stream.collect(Collectors.toList());
    }

    @Override
    public AccountStatus deactivateAccount(String username) {
        try{
            AccountEntity currentAccount = accountRepository.findByUsername(username).orElseThrow(
                    () -> new AccountNotFoundException("No account exists with the given id")
            );
            currentAccount.setStatus(false);
            accountRepository.save(currentAccount);
            return new AccountStatus().accountQuerySuccess();
        } catch (AccountNotFoundException e){
            return new AccountStatus().accountFailed(e);
        }


    }

    @Override
    public AccountStatus reactivateAccount(String username) {
        try {
            AccountEntity currentAccount = accountRepository.findByUsername(username).orElseThrow(
                    () -> new AccountNotFoundException("No account exists with the given id")
            );
            currentAccount.setStatus(true);
            accountRepository.save(currentAccount);
            return new AccountStatus().accountQuerySuccess();
        } catch (AccountNotFoundException e){
            return new AccountStatus().accountFailed(e);
        }
    }



    @Override
    public AccountStatus updateAccount(String username) {
        return null;
    }

    @Override
    public AccountStatus updateAccount(Integer id) {
        return null;
    }


    // Throws a HibernateException if there already exists an account with the given username or password
    private void findExistingAccountConflicts(String username, String password) {
        List<AccountEntity> list = accountRepository.findExistingConflicts(username, password);
        if(list != null)
            throw new HibernateException("Either the username or password is already taken");
    }


    // Converts a AccountEntity to a Jackson Account Object
    private Account toJacksonAccountObject(AccountEntity a){
        return new Account(a.getUsername(), a.getPassword(), a.getFirst_name(), a.getLast_name(), a.getEmail());
    }

    // Converts a Jackson Account object to a Hibernate Account Object
    private AccountEntity createNewAccountEntry(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setFirst_name(account.first_name);
        entity.setLast_name(account.last_name);
        entity.setUsername(account.username);
        entity.setPassword(account.password);
        entity.setEmail(account.email);
        entity.setStatus(true);
        return entity;
    }
}
