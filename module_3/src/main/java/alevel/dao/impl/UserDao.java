package alevel.dao.impl;

import alevel.dao.BaseAccountDao;
import alevel.dao.BaseDao;
import alevel.dao.BaseOperationDao;
import alevel.dto.table.ResponseAbstTablePage;
import alevel.dto.table.impl.ResponseAccountsUserTable;
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
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                log.info("User create: " + update.getUsername());
            }
        } else {
            log.info("User don't have with id: " + update.getId());
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
        log.info("User remove with id: " + id);
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

        ResponseUserTablePage responseUserTablePage = new ResponseUserTablePage();

        responseUserTablePage.setContent(list);
        responseUserTablePage.setPage(page);
        responseUserTablePage.setTotalPages(totalPages);
        responseUserTablePage.setShowEntity(showEntity);
        responseUserTablePage.setAllSizeEntity(itemSize);
        responseUserTablePage.setSort(sort);
        responseUserTablePage.setShowEntityFrom(entFrom);
        responseUserTablePage.setShowEntityTo(entTo);
        log.info("Create page user : " + responseUserTablePage.toString());
        return responseUserTablePage;
    }

    @Override
    public ResponseAbstTablePage findAllAccountForUserListPage(int page, int showEntity, String column, String sort, Long userId) {
        List<Account> list = new ArrayList<>();
        int firstPage = (page - 1) * showEntity;
        try {
            String sql = String.format("select id, createAccount, money, nameAccount from Account  where id in (select usr_ac.accounts_id from User_Account usr_ac where usr_ac.User_id = %d) order by %s  %s limit %d, %d", userId, column, sort, firstPage, showEntity);
            Query query = entityManager
                    .createNativeQuery(sql, Account.class);
            list = query.getResultList();
        } catch (Exception e) {
            log.info(new Date() + " " + e.getMessage());
        }

        //TODO new count Account user
        int itemSize = countUserEntity();
        int totalPages = totalPage(itemSize, showEntity);
        int entFrom = showEntriesFrom(page, showEntity);
        int entTo = showEntriesTo(entFrom, itemSize);

        ResponseAccountsUserTable responseAccountsUserTable = new ResponseAccountsUserTable();

        responseAccountsUserTable.setContent(list);
        responseAccountsUserTable.setPage(page);
        responseAccountsUserTable.setTotalPages(totalPages);
        responseAccountsUserTable.setShowEntity(showEntity);
        responseAccountsUserTable.setAllSizeEntity(itemSize);
        responseAccountsUserTable.setSort(sort);
        responseAccountsUserTable.setShowEntityFrom(entFrom);
        responseAccountsUserTable.setShowEntityTo(entTo);
        log.info("Create page account : " + responseAccountsUserTable.toString());
        return responseAccountsUserTable;
    }

    @Override
    public void exportAccountOperationByUserToCSV(HttpServletResponse response, Long userId) throws Exception {
        List<Operation> operations = new ArrayList<>();
        List<Account> accounts = findAllAccountsByUserId(userId);
        for (Account account : accounts) {
            Operation operationByAccountId = findOperationByAccountId(account.getId());
            operations.add(operationByAccountId);
        }
        convertToCSV(response, operations);
    }

    private void convertToCSV(HttpServletResponse response, List<Operation> list) throws Exception {

        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);


        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"id", "dateOperation", "operationFinance", "recipient", "sender"};
        String[] mapping = {"id", "dateOperation", "operationFinance", "recipient", "sender"};


        csvWriter.writeHeader(csvHeader);

        for (Operation operation : list) {
          csvWriter.write(operation, mapping);

        }

        csvWriter.close();
    }

    private Operation findOperationByAccountId(Long accountId) {
        Operation operation = null;
        try {
            Query query = entityManager
                    .createNativeQuery("select id, dateOperation, operationFinance, recipient, sender from Operation op where op.id in (select operation_id from Account_Operation where Account_id = :id)", Operation.class)
                    .setParameter("id", accountId);
            operation = (Operation) query.getResultList().get(0);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return operation;

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
        log.info("Method createAccount: " + "create acount with id " + account.getId());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void updateAccount(Account account) {
        entityManager.merge(account);
        log.info("success update :"+ account.getId());
    }

    @Override
    public void deleteAccount(Long accountId) {
        if (!existAccountById(accountId)) {
            throw new UsernameExistsException("Account not found with id: " + accountId);
        }
        entityManager.
                createNativeQuery("delete from User_Account where accounts_id = :id")
                .setParameter("id", accountId)
                .executeUpdate();
        log.info("account with id sucess delete : " + accountId);
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
        log.info("Create operation : " + operation);
    }

    @Override
    public void updateOperation(Operation operation) {
        entityManager.merge(operation);
        log.info("Update operation : " + operation);
    }

    @Override
    public void deleteOperation(Long id) {
        Operation operation = new Operation();
        operation.setId(id);
        entityManager.remove(operation);
        log.info("Delete operation with id: " + id);
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
            log.info(e.getMessage());
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
            log.info(e.getMessage());
        }
        return operations;
    }

    public boolean sendMoneyToUser(Long senderId, Long recipientId, Long acccountSenderId, Long accountRecipientId, long summa) throws Exception {

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
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        throw new Exception("You have little many");
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
