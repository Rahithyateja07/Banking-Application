package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Accounts;
import com.example.demo.models.Transactions;
import com.example.demo.repositories.AccountsRepository;
import com.example.demo.repositories.TransactionsRepository;

@RestController
public class AccountsController {
	
	@Autowired
    private AccountsRepository accountsRepo;
	@Autowired
    private TransactionsRepository transactionRepository;

    
    @PostMapping("/register")
    public Accounts register(@RequestBody Accounts account) {
        return accountsRepo.save(account);
        
    }
    
    @PostMapping("/login")
    public boolean login(@RequestParam int customerID, @RequestParam String password) {
        return accountsRepo.existsByCustomerIDAndPassword(customerID, password);
    }
    
    @PutMapping("/changepwd")
    public boolean changePassword(@RequestParam int customerID, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Optional<Accounts> accountOpt = accountsRepo.findByCustomerID(customerID);
        if (accountOpt.isPresent() && accountOpt.get().getPassword().equals(oldPassword)) {
            Accounts account = accountOpt.get();
            account.setPassword(newPassword);
            accountsRepo.save(account);
            return true;
        }
        return false;
    }
    
    
    @PostMapping("/transfer")
    public String transfer(@RequestParam int fromAccountNumber, @RequestParam int toAccountNumber,
                           @RequestParam String ifsc, @RequestParam double amount) {
        Accounts fromAccount = accountsRepo.findByAccountNumber(fromAccountNumber).orElse(null);
        Accounts toAccount = accountsRepo.findByAccountNumber(toAccountNumber).orElse(null);

        // Checking if both accounts exists or not
        if (fromAccount == null || toAccount == null) {
            return "One or both accounts do not exist.";
        }

        // Checking if the  IFSC code matches for the receiving account
        if (!toAccount.getIfsc().equals(ifsc)) {
            return "IFSC code mismatch for the receiving account.";
        }

        // Check if the fromAccount has enough balance
        if (fromAccount.getBalance() < amount) {
            return "Insufficient funds in the source account.";
        }

      
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);


        accountsRepo.save(fromAccount);
        accountsRepo.save(toAccount);

        // Recording the transaction
        Transactions transaction = new Transactions();
        transaction.setFromAccount(fromAccountNumber);
        transaction.setToAccount(toAccountNumber);
        transaction.setAmount(amount);
        transaction.setIfsc(ifsc);
        transactionRepository.save(transaction);

        return "Transfer completed successfully.";
    }
    
    @GetMapping("/balance/{accountNumber}")
    public double getBalance(@PathVariable int accountNumber) {
        Optional<Accounts> account = accountsRepo.findByAccountNumber(accountNumber);
        return account.map(Accounts::getBalance).orElse(0.0);
    }

    @GetMapping("/accounts/below")
    public List<Accounts> getAccountsBelow(@RequestParam double amount) {
        return accountsRepo.findByBalanceLessThan(amount);
    }

    @GetMapping("/accounts/above")
    public List<Accounts> getAccountsAbove(@RequestParam double amount) {
        return accountsRepo.findByBalanceGreaterThan(amount);
    }

}
