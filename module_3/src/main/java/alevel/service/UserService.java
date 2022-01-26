package alevel.service;

import alevel.dto.table.impl.ResponseUserTablePage;
import alevel.model.impl.Account;
import alevel.model.impl.Operation;
import alevel.model.impl.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    void create(User t);
    void update(User t);
    void delete(Long id);
    User findById(Long id);

    public void createAccount(Account account, Long userId);

    public void updateAccount(Account account);

    public void deleteAccount(Long accountId);

    public Account findAccountById(Long id);

    public Account findAccountByNameAccount(String nameAccount);

    public List<Account> findAllAccountsByUserId(Long id);

    public void createOperation(Operation operation);

    public void updateOperation(Operation operation);

    public void deleteOperation(Long id);

    public Operation findOperationById(Long id);

    public Operation findByAccountId(Long accountId);

    public List<Operation> findAllByAccountId(Long id);

     void sendMoneyToUser(Long senderId, Long recipientId, Long acccountSenderId, Long accountRecipientId, long summa);

     ResponseUserTablePage findAllWithSortColumn(int page, int showEntity, String column, String sort);
}
