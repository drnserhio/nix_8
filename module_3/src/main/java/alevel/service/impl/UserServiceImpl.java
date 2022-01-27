package alevel.service.impl;

import alevel.dao.impl.UserDao;
import alevel.dto.table.ResponseUserTablePage;
import alevel.model.impl.Account;
import alevel.model.impl.Operation;
import alevel.model.impl.User;
import alevel.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void createAccount(Account account, Long userId) {
        userDao.createAccount(account, userId);
    }

    @Override
    public void updateAccount(Account account) {
        userDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        userDao.deleteAccount(accountId);
    }

    @Override
    public Account findAccountById(Long id) {
        return userDao.findAccountById(id);
    }

    @Override
    public Account findAccountByNameAccount(String nameAccount) {
        return userDao.findAccountByNameAccount(nameAccount);
    }

    @Override
    public List<Account> findAllAccountsByUserId(Long id) {
        return userDao.findAllAccountsByUserId(id);
    }

    @Override
    public void createOperation(Operation operation) {
        userDao.createOperation(operation);
    }

    @Override
    public void updateOperation(Operation operation) {
        userDao.updateOperation(operation);
    }

    @Override
    public void deleteOperation(Long id) {
        userDao.deleteOperation(id);
    }


    @Override
    public Operation findOperationById(Long id) {
       return userDao.findOperationById(id);
    }

    @Override
    public Operation findByAccountId(Long accountId) {
        return userDao.findByAccountId(accountId);
    }

    @Override
    public List<Operation> findAllByAccountId(Long id) {
        return userDao.findAllByAccountId(id);
    }

    @Override
    public void sendMoneyToUser(Long senderId, Long recipientId, Long acccountSenderId, Long accountRecipientId, long summa) {
        userDao.sendMoneyToUser(senderId, recipientId, acccountSenderId, accountRecipientId, summa);
    }

    @Override
    public ResponseUserTablePage findAllWithSortColumn(int page, int showEntity, String column, String sort) {
        return userDao.findAllWithSortColumn(page, showEntity, column, sort);
    }
}
