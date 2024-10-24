package t1.java.demo.registerAndTransaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1.java.demo.registerAndTransaction.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
