package alevel.resource;

import alevel.dto.table.ResponseAbstTablePage;
import alevel.model.impl.Account;
import alevel.model.impl.Operation;
import alevel.model.impl.User;
import alevel.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin("http://localhost:4999")
public class UserResources {

    private UserService userService;


    @GetMapping("/list-user")
    public List<User> findAll() {
        return userService.findAll();
    }


    @PostMapping("/create_user")
    public void create(
            @RequestBody User user) {
        userService.create(user);
    }


    @PutMapping("/update_user")
    public void update(
            @RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/delete_user/{id}")
    public void delete(
            @PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/find_user/{id}")
    public User findById(
            @PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/create_account/{userId}")
    public void createAccount(
            @RequestBody Account account,
            @PathVariable Long userId) {
        userService.createAccount(account, userId);
    }

    @PutMapping("/update_account")
    public void updateAccount(
            @RequestBody Account account) {
        userService.updateAccount(account);
    }


    @DeleteMapping("/delete_account/{accountId}")
    public void deleteAccount(
            @PathVariable Long accountId) {
        userService.deleteAccount(accountId);
    }


    @GetMapping("/find_account/{id}")
    public Account findAccountById(
            @PathVariable Long id) {
        return userService.findAccountById(id);
    }

    @GetMapping("/find_account_by_name/{nameAccount}")
    public Account findAccountByNameAccount(
            @PathVariable String nameAccount) {
        return userService.findAccountByNameAccount(nameAccount);
    }

    @GetMapping("/list_accounts_user/{userId}")
    public List<Account> findAllAccountsByUserId(
            @PathVariable Long userId) {
        return userService.findAllAccountsByUserId(userId);
    }

    @GetMapping("/find_operation/{id}")
    public Operation findOperationById(
            @PathVariable Long id) {
        return userService.findOperationById(id);
    }

    @GetMapping("/find_operation_account/{accountId}")
    public Operation findByAccountId(
            @PathVariable Long accountId) {
        return userService.findByAccountId(accountId);
    }


    public List<Operation> findAllByAccountId(Long id) {
        return userService.findAllByAccountId(id);
    }


    @GetMapping("/send/{senderId}/{recipientId}/{acccountSenderId}/{accountRecipientId}/{summa}")
    public void sendMoney(
            @PathVariable Long senderId,
            @PathVariable Long recipientId,
            @PathVariable Long acccountSenderId,
            @PathVariable Long accountRecipientId,
            @PathVariable Long summa) {
        userService.sendMoneyToUser(senderId, recipientId, acccountSenderId, accountRecipientId, summa);
    }

    @GetMapping("/limit-list/users/{page}/{showEntity}/{column}/{sort}")
    public ResponseAbstTablePage findAllUserListPage(
            @PathVariable("page") int page,
            @PathVariable( "showEntity") int showEntity,
            @PathVariable("column") String columnSort,
            @PathVariable("sort") String sort) {
        ResponseAbstTablePage tb = userService.findAllWithSortColumn(page, showEntity, columnSort, sort);
        return tb;
    }

    @GetMapping("/limit-accounts_user/users/{page}/{showEntity}/{column}/{sort}/{id}")
    public ResponseAbstTablePage findAllAccountForUserListPage(
            @PathVariable("page") int page,
            @PathVariable( "showEntity") int showEntity,
            @PathVariable("column") String columnSort,
            @PathVariable("sort") String sort,
            @PathVariable("id") Long userId) {
        ResponseAbstTablePage tb = userService.findAllAccountForUserListPage(page, showEntity, columnSort, sort, userId);
        return tb;
    }
}
