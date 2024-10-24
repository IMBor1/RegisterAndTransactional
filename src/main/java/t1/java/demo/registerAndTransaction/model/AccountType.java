package t1.java.demo.registerAndTransaction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountType {
    DEBIT,
    CREDIT,
    DEPOSIT
}
