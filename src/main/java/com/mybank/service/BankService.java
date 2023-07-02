package com.mybank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mybank.database.BankRepository;
import com.mybank.database.MyBankDB;

@Service
public class BankService {

@Autowired
BankRepository bankRepository;
@Autowired
MyBankDB myBankDB;

public void createUser(MyBankDB db)
{
bankRepository.save(db);
}

public void addBalance(MyBankDB db,Integer Amount)
{
//Integer currentBal=5500;
Integer currentBal=db.getAccountBal().intValue();
db.setAccountBal(currentBal+Amount);
bankRepository.save(db);
}

public void withdrawbal(MyBankDB db,Integer Amount)
{
Integer currentBal = db.getAccountBal().intValue();
db.setAccountBal(currentBal-Amount);
bankRepository.save(db);
}

public Integer checkBalance()
{
	Integer currentBal = myBankDB.getAccountBal();
	return currentBal; 
}
}