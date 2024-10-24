package t1.java.demo.registerAndTransaction.service;

import t1.java.demo.registerAndTransaction.model.Account;

public interface AccountService {
    Account registerAccount(Account account);

    void blockAccount(Long accountId);

    void unblockAccount(Long accountId);
}
