package t1.java.demo.registerAndTransaction.service.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import t1.java.demo.registerAndTransaction.model.Account;
import t1.java.demo.registerAndTransaction.model.AccountType;
import t1.java.demo.registerAndTransaction.model.Transaction;
import t1.java.demo.registerAndTransaction.model.TransactionType;
import t1.java.demo.registerAndTransaction.repository.AccountRepository;
import t1.java.demo.registerAndTransaction.service.AccountService;

@Slf4j
@AllArgsConstructor
@Service
public class AccountServiceimpl implements AccountService {

    private AccountRepository accountRepository;
    private final KafkaTemplate kafkaTemplate;
    private final TransactionServiceImpl transactionService;

    @Override
    public Account registerAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void blockAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow();
        account.setStatus(String.valueOf(TransactionType.LOCK));
        accountRepository.save(account);
    }

    @Override
    public void unblockAccount(Long accountId) {
        try {
            Account account = accountRepository.findById(accountId).orElseThrow();
            if (account.getAccountType().equals(AccountType.CREDIT)
                    && account.getBalance() < account.getBalance()) {
                account.setStatus(String.valueOf(TransactionType.LOCK));
                accountRepository.save(account);
                log.info("Транзакция не может быть разблокирована");
            }
            Transaction pendingTransaction = transactionService.getPendingTransactionForAccount(accountId);
            if (pendingTransaction != null) {
                kafkaTemplate.send("t1_demo_client_transactions", pendingTransaction);
                log.info("Счет разблокирован и транзакция обработана повторно.");
                return;
            } else {
                log.info("Недостаточно средств для разблокировки кредитного счета.");
            }
            if (account.getAccountType().equals(AccountType.DEPOSIT)) {
                accountRepository.findById(accountId).orElseThrow().setStatus(String.valueOf(TransactionType.UNLOCK));
                log.info("Депозитный счет разблокирован.");
                accountRepository.save(account);
                return;
            } else {
                log.info("Некорректный тип счета.");
                return;
            }

        } catch (Exception e) {
            log.info("Ошибка при разблокировке счета: " + e.getMessage());
            return;
        }
    }
}





