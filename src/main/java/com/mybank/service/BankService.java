package com.mybank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mybank.database.BankRepository;
import com.mybank.database.MyBankDB;

@Service
public class BankService {

@Autowired
BankRepository bankRepository;

public void createUser(MyBankDB db)
{
bankRepository.save(db);
}

public MyBankDB getById(Integer id) {
    return bankRepository.findById(id).orElse(null);
}


public Integer checkBalance(Integer id)
{
MyBankDB db = bankRepository.findById(id).orElse(null);
Integer accountBal = db.getAccountBal();
return accountBal;
}

public void addBalance(Integer id, Integer Amount) {
    MyBankDB db = bankRepository.findById(id).orElse(null);
    if (db != null) {
    	Integer accountBal = db.getAccountBal();
        db.setAccountBal(accountBal+Amount);
        bankRepository.save(db);  }
    }
    public void withdrawBalance(Integer id, Integer Amount) {
        MyBankDB db = bankRepository.findById(id).orElse(null);
        if (db != null) {
        	Integer accountBal = db.getAccountBal();
            db.setAccountBal(accountBal-Amount);
            bankRepository.save(db);
        }
      
      
    
}
}

