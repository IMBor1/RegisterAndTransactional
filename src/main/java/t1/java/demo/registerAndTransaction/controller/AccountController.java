package t1.java.demo.registerAndTransaction.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1.java.demo.registerAndTransaction.model.Account;
import t1.java.demo.registerAndTransaction.service.AccountService;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.registerAccount(account));
    }

    @PostMapping("/{accountId}/block")
    public ResponseEntity<Void> blockAccount(@PathVariable Long accountId) {
        accountService.blockAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{accountId}/unblock")
    public ResponseEntity<Void> unblockAccount(@PathVariable Long accountId) {
        accountService.unblockAccount(accountId);
        return ResponseEntity.noContent().build();
    }

}
