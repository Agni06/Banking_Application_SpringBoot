package com.banking_application.MyBanking.Service;

import com.banking_application.MyBanking.Entity.Account;
import com.banking_application.MyBanking.Repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountSetvice {

    @Autowired
    AccountDAO accountDAO;

    public ResponseEntity<List<Account>> getALLAccounts() {
        return new ResponseEntity<>(accountDAO.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Account> addAccount(Account account) {
        try {
            return new ResponseEntity<>(accountDAO.save(account), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    public ResponseEntity<String> withdraw(int id, Long amount) throws Exception {
        Account account = accountDAO.findById(id).orElseThrow(()->new Exception("Account Not Found"));


        Long balance=account.getBalance();

        if(balance < amount) {
            return new ResponseEntity<>("Amount is Insufficient",HttpStatus.BAD_REQUEST);
        }

        balance=balance-amount;
        account.setBalance(balance);
        accountDAO.save(account);
        return new ResponseEntity<>("Successfully Withdrawn",HttpStatus.OK);

    }

    public ResponseEntity<Account> getAccountById(int id) throws Exception {
        Account account=accountDAO.findById(id).orElseThrow(()->new Exception("Account Not Found"));

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    public ResponseEntity<String> deposit(int id, Long amount) throws Exception {
        Account account=accountDAO.findById(id).orElseThrow(()-> new Exception("Account Not Found"));
        account.setBalance(account.getBalance()+amount);
        accountDAO.save(account);
        return new ResponseEntity<>("Successfully Deposited",HttpStatus.OK);
    }

    public ResponseEntity<String> updateAccountDetails(int id, Account account) throws Exception {
        Account existingAccount=accountDAO.findById(id).orElseThrow(()->new Exception("Account Not Found"));

        if(account.getAccountType()!=null){
            existingAccount.setAccountType(account.getAccountType());
        }
        if(account.getAccountHolderName()!=null){
            existingAccount.setAccountHolderName(account.getAccountHolderName());
        }
        if(account.getAccountNumber()!=null){
            existingAccount.setAccountNumber(account.getAccountNumber());
        }

        accountDAO.save(existingAccount);
        return new ResponseEntity<>("Successfully Updated",HttpStatus.OK);
    }
}
