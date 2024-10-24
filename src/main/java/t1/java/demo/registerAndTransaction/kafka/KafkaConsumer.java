package t1.java.demo.registerAndTransaction.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import t1.java.demo.registerAndTransaction.model.Account;
import t1.java.demo.registerAndTransaction.model.Transaction;
import t1.java.demo.registerAndTransaction.model.TransactionType;
import t1.java.demo.registerAndTransaction.repository.AccountRepository;
import t1.java.demo.registerAndTransaction.service.AccountService;
import t1.java.demo.registerAndTransaction.service.impl.TransactionServiceImpl;

@AllArgsConstructor
@Service
public class KafkaConsumer {

    private final TransactionServiceImpl transactionService;
    private final AccountRepository accountRepository;

    private final AccountService accountService;


    private final KafkaTemplate<String, Long> kafkaTemplate;

    @KafkaListener(topics = "t1_demo_client_transactions", groupId = "group_id")
    public void listen(Transaction transaction) {
        try {
            Account account = accountService.registerAccount(transaction.getAccount());

            if ("BLOCKED".equals(account.getStatus())) {
                kafkaTemplate.send("t1_demo_client_transaction_errors", transaction.getId());
                return;
            }

            if (transaction.getTransactionType() == TransactionType.DEBIT) {
                account.setBalance(account.getBalance());
            } else if (transaction.getTransactionType() == TransactionType.CREDIT) {
                account.setBalance(account.getBalance());
            }

            accountRepository.save(account);

            transactionService.createTransaction(transaction);

        } catch (Exception e) {
            System.err.println("Ошибка обработки транзакции: " + e.getMessage());
        }
    }
}
