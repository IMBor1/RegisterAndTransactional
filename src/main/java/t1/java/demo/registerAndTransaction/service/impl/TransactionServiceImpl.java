package t1.java.demo.registerAndTransaction.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t1.java.demo.registerAndTransaction.model.Transaction;
import t1.java.demo.registerAndTransaction.repository.TransactionRepository;
@AllArgsConstructor
@Setter
@Getter
@Service
public class TransactionServiceImpl {

    private final TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
