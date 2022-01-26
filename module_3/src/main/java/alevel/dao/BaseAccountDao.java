package alevel.dao;

import alevel.model.impl.Account;

import java.util.List;

public interface BaseAccountDao<T>{

    void createAccount(T t, Long userId);
    void updateAccount(T t);
    void deleteAccount(Long id);
    boolean existAccountById(Long id);
    boolean existsAccountByNameAccount(String nameAccount);
    Account findAccountById(Long id);
    Account findAccountByNameAccount(String nameAccount);


    List<Account> findAllAccountsByUserId(Long id);






}
