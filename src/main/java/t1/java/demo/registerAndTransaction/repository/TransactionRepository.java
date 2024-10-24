package t1.java.demo.registerAndTransaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1.java.demo.registerAndTransaction.model.Transaction;
import t1.java.demo.registerAndTransaction.model.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findFirstByAccount_IdAndTransactionType(Long accountId, TransactionType transactionType);
}

