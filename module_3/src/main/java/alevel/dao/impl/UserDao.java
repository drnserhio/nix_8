package alevel.dao.impl;

import alevel.dao.BaseAccountDao;
import alevel.dao.BaseDao;
import alevel.dao.BaseOperationDao;
import alevel.dto.table.impl.ResponseUserTablePage;
import alevel.model.impl.MoneyTransaction;
import alevel.exception.AccountExistException;
import alevel.exception.UsernameExistsException;
import alevel.model.impl.Account;
import alevel.model.impl.Operation;
import alevel.model.impl.User;
import alevel.util.ConverterMoneyUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
@Slf4j
public class UserDao implements BaseDao<User>, BaseAccountDao<Account>, BaseOperationDao<Operation> {

    private final EntityManager entityManager;


    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("select user from User user", User.class)
                .getResultList();
    }


    @Override
    public void create(User user) {
        if (existById(user.getId())) {
            throw new UsernameExistsException("User with id already taken");
        }
        if (existByUsername(user.getUsername())) {
            throw new UsernameExistsException("User with username already taken");
        }
        entityManager.persist(user);
    }

    @Override
    public void update(User update) {
        if (existById(update.getId())) {
            User usr = findByUsername(update.getUsername());
            if (usr == null) {
                entityManager.merge(update);
            } else if (usr.getId().equals(update.getId())) {
                usr.setFirstname(update.getFirstname());
                usr.setLastname(update.getLastname());
                usr.setUsername(update.getUsername());
                usr.setUsername(update.getUsername());
                usr.setPhone(update.getPhone());
                usr.setAccounts(update.getAccounts());
                entityManager.merge(usr);
            }
        } else {
            throw new UsernameExistsException("User don't have with id: " + update.getId());
        }
    }

    @Override
    public void delete(Long id) {
        if (!existById(id)) {
            throw new UsernameExistsException("User not found with id: " + id);
        }
        User user = new User();
        user.setId(id);
        entityManager.remove(user);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager
                .createQuery("select count (usr.id) from User usr where usr.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            Query query = entityManager
                    .createQuery("select usr from User usr where usr.id = :id")
                    .setParameter("id", id);
            user = (User) query.getResultList().get(0);
        } catch (Exception e) {
            log.info(new Date() + " " + e.getMessage());
        }
        return user;
    }

    @Override
    public ResponseUserTablePage findAllWithSortColumn(int page, int showEntity, String column, String sort) {

        List<User> list = new ArrayList<>();
        int firstPage = (page - 1) * showEntity;
        try {
            String sql = String.format("select * from User order by %s %s limit %d, %d", column, sort, firstPage, showEntity);
            Query query =  entityManager
                    .createNativeQuery(sql, User.class);
            list = query.getResultList();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }



        int itemSize = countUserEntity();
        int totalPages = totalPage(itemSize, showEntity);
        int entFrom = showEntriesFrom(page, showEntity);
        int entTo = showEntriesTo(entFrom, itemSize);

        ResponseUserTablePage departmentResponse = new ResponseUserTablePage();

        departmentResponse.setUsers(list);
        departmentResponse.setPage(page);
        departmentResponse.setTotalPages(totalPages);
        departmentResponse.setShowEntity(showEntity);
        departmentResponse.setAllSizeEntity(itemSize);
        departmentResponse.setSort(sort);
        departmentResponse.setShowEntityFrom(entFrom);
        departmentResponse.setShowEntityTo(entTo);
        return departmentResponse;
    }

    public int countUserEntity() {
        int count = 0;
        try {
            Query query =  entityManager
                    .createQuery("select (id) from User ");
            count = query.getResultList().size();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return count;
    }

    private int totalPage(int itemSize, int showEntity) {
        if (itemSize % showEntity == 0) {
            return (itemSize / showEntity);
        } else {
            return (itemSize / showEntity) + 1;
        }
    }

    private int showEntriesTo(int entFrom, int itemSize) {
        return entFrom - 1 + itemSize;
    }

    private int showEntriesFrom(int page, int showEntity) {
        return (page - 1) * showEntity + 1;
    }

    private boolean existByUsername(String username) {
        Query query = entityManager
                .createQuery("select count (usr.username) from User usr where usr.username = : username")
                .setParameter("username", username);
        return (Long) query.getSingleResult() == 1;
    }

    public User findByUsername(String username) {
        User user = null;
        try {
            Query query = entityManager
                    .createQuery("select usr from User usr where usr.username = :username")
                    .setParameter("username", username);
            user = (User) query.getResultList().get(0);
        } catch (Exception e) {
            log.info(new Date() + " " + e.getMessage());
        }
        return user;
    }


    @Override
    public void createAccount(Account account, Long userId) {
        if (existAccountById(account.getId())) {
            throw new AccountExistException("Account with id:" + account.getId() + " already taken");
        }
        if (existsAccountByNameAccount(account.getNameAccount())) {
            throw new AccountExistException("Account with name: " + account.getNameAccount() + " already taken");
        }
        account.setCreateAccount(LocalDate.now());
        saveAccount(account);
        entityManager
                .createNativeQuery("insert into User_Account values (:usr_id, :ac_id)")
                .setParameter("usr_id", userId)
                .setParameter("ac_id", account.getId()).executeUpdate();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        if (!existAccountById(accountId)) {
            throw new UsernameExistsException("Account not found with id: " + accountId);
        }

        //TODO: refactor
        Account ac = new Account();
        ac.setId(accountId);
        entityManager.remove(ac);
    }

    @Override
    public boolean existAccountById(Long id) {
        Query query = entityManager
                .createQuery("select count(ac.id) from Account ac where ac.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public boolean existsAccountByNameAccount(String nameAccount) {
        Query query = entityManager
                .createQuery("select count (ac.nameAccount) from Account ac where ac.nameAccount = : nameAccount")
                .setParameter("nameAccount", nameAccount);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Account findAccountById(Long id) {
        Account account = null;
        try {
            account = entityManager.find(Account.class, id);
        } catch (Exception e) {
            log.info(new Date() + " " + e.getMessage());
        }
        return account;
    }

    @Override
    public Account findAccountByNameAccount(String nameAccount) {
        Account account = null;
        Query query = entityManager.createQuery("select ac from Account ac where ac.nameAccount = :nameAccount")
                .setParameter("nameAccount", nameAccount);
        account = (Account) query.getResultList();
        return account;
    }

    @Override
    public List<Account> findAllAccountsByUserId(Long id) {
        List<Account> list = new ArrayList<>();
        try {
            Query query = entityManager
                    .createNativeQuery("select id, createAccount, money, nameAccount from Account where id in (select usr_ac.accounts_id from User_Account usr_ac where usr_ac.User_id = :id)", Account.class)
                    .setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            log.info(new Date() + " " + e.getMessage());
        }
        return list;
    }

    @Override
    public void createOperation(Operation operation) {
        entityManager.persist(operation);
    }

    @Override
    public void updateOperation(Operation operation) {
        entityManager.merge(operation);
    }

    @Override
    public void deleteOperation(Long id) {
        Operation operation = new Operation();
        operation.setId(id);
        entityManager.remove(operation);
    }

    @Override
    public boolean existOperationById(Long id) {
        Query query = entityManager
                .createQuery("select count(op.id) from Operation op  where op.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Operation findOperationById(Long id) {
        Operation operation = null;
        Query query = entityManager.createQuery("select op from Operation op where op.id = :id")
                .setParameter("id", id);
        operation = (Operation) query.getResultList();
        return operation;
    }

    @Override
    public Operation findByAccountId(Long accountId) {
        Operation operation = null;
        try {
            Query query = entityManager
                    .createQuery("select ac from Account ac where ac.id = : accountId", Operation.class)
                    .setParameter("accountId", accountId);
            operation = (Operation) query.getResultList().get(0);
        } catch (Exception e) {
            log.info(new Date() + " " + e.getMessage());
        }
        return operation;
    }

    @Override
    public List<Operation> findAllByAccountId(Long id) {
        List<Operation> operations = new ArrayList<>();
        try {
            Query query = entityManager
                    .createNativeQuery("select id, dateOperation, operationFinance from Operation " +
                            "where id in " +
                            "(select operation_id from Account_Operation where Account_id = : id)", Operation.class)
                    .setParameter("id", id);
            operations = query.getResultList();
        } catch (Exception e) {
            log.info(new Date() + " " + e.getMessage());
        }
        return operations;
    }

    public void sendMoneyToUser(Long senderId, Long recipientId, Long acccountSenderId, Long accountRecipientId, long summa) {
        if (!existById(senderId) ||
                !existById(recipientId)) {
            throw new UsernameExistsException("User not found");
        }
        if (!existAccountById(acccountSenderId) ||
                !existAccountById(accountRecipientId)) {
            throw new AccountExistException("Account not found");
        }
        User sender = findById(senderId);
        User recipient = findById(recipientId);
        Account senderAccount = findAccountById(acccountSenderId);
        Account accountRecipient = findAccountById(accountRecipientId);
        MoneyTransaction moneyTransaction = null;
        try {
            moneyTransaction = ConverterMoneyUtil.uah(sender, recipient, senderAccount, accountRecipient, summa);
            updateAccount(moneyTransaction.getSenderUpdate());
            updateAccount(moneyTransaction.getRecipientUpdate());
            createOperation(moneyTransaction.getOperationSender());
            createOperation(moneyTransaction.getOperationRecipient());

            insertIntoSender(senderAccount.getId(),
                    moneyTransaction.getOperationSender().getId());

            insertIntoRecipient(accountRecipient.getId(),
                    moneyTransaction.getOperationRecipient().getId());
        } catch (Exception e) {
            log.info(e.getMessage());
        }


    }

    public void insertIntoRecipient(Long recipientAccountId, Long recipientOperationId) {
        try {
            entityManager
                    .createNativeQuery("insert into Account_Operation values (:ac_id, :op_id)")
                    .setParameter("ac_id", recipientAccountId)
                    .setParameter("op_id", recipientOperationId)
                    .executeUpdate();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void insertIntoSender(Long senderAccountId, Long senderOperationId) {
        try {
            entityManager
                    .createNativeQuery("insert into Account_Operation values (:ac_id, :op_id)")
                    .setParameter("ac_id", senderAccountId)
                    .setParameter("op_id", senderOperationId)
                    .executeUpdate();
        } catch (Exception e) {
            log.info(e.getMessage() + " " + "insertIntoAccountAndOperation method");
        }
    }

    private void saveAccount(Account account) {
        entityManager.persist(account);
    }

}
