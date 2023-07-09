package com.mybank.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mybank.database.BankRepository;
import com.mybank.database.MyBankDB;

@Service
public class BankService {

@Autowired
BankService bankService;

@Autowired
BankRepository bankRepository;

public Boolean login(Integer id,String password)
{
MyBankDB user = bankRepository.findById(id).orElse(null);
Boolean login=false;
if(bankRepository.existsById(id))
{
	if(password.equalsIgnoreCase(user.getPassword()))
	{
		login=true;
	}
}
return login;
}

public Boolean verifyPassword(Integer id,String senderPassword)
{
	MyBankDB sender = bankService.getById(id);
	String AcPassword = sender.getPassword();
    Boolean verify=false;
	if(AcPassword.equalsIgnoreCase(senderPassword))
	{
		return verify=true;
	}
	return verify;
}

public void createUser(MyBankDB db)
{
bankRepository.save(db);
}

public MyBankDB getById(Integer id) {
    return bankRepository.findById(id).orElse(null);
}

public List<MyBankDB> findAll()
{
	List<MyBankDB> allUsers = bankRepository.findAll();
	return allUsers;
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
    
    public Boolean transfer(MyBankDB receiver,Integer senderAcNum,Integer TransferAmount)
    {
    	boolean receiverExist = bankRepository.existsById(receiver.getAccountNum());
    	
    	Integer senderAccountBal = bankService.checkBalance(senderAcNum);
    	Integer receiverAccountNum = receiver.getAccountNum();
    	System.out.println(receiverAccountNum);
    	Boolean error=false;
    	
    if(senderAccountBal>TransferAmount && receiverExist)
    {    
    		
    		bankService.withdrawBalance(senderAcNum, TransferAmount);
    		bankService.addBalance(receiverAccountNum, TransferAmount);
    }
    else
    {
    	error=true;
    }
  return error;
    
    
    }
    
}

