package t1.java.demo.registerAndTransaction.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import t1.java.demo.registerAndTransaction.model.Account;
import t1.java.demo.registerAndTransaction.model.AccountType;
import t1.java.demo.registerAndTransaction.model.Transaction;
import t1.java.demo.registerAndTransaction.model.TransactionType;
import t1.java.demo.registerAndTransaction.repository.AccountRepository;
import t1.java.demo.registerAndTransaction.service.AccountService;

@AllArgsConstructor
@Service
    public class AccountServiceimpl implements AccountService {
        
        private AccountRepository accountRepository;

        @Override
        public Account registerAccount(Account account) {
            return accountRepository.save(account);
        }

        @Override
        public void blockAccount(Long accountId) {
            Account account = accountRepository.findById(accountId).orElseThrow();
            account.setStatus(String.valueOf(TransactionType.CANCEL));
            accountRepository.save(account);
        }

        @Override
        public void unblockAccount(Long accountId) {
            Account account = accountRepository.findById(accountId).orElseThrow();
            account.setStatus(String.valueOf(AccountType.DEBIT));
            accountRepository.save(account);
        }
    }


