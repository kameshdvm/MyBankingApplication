package com.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.mybank.database.MyBankDB;
import com.mybank.service.BankService;



@RestController
public class BankController 
{	
@Autowired	
BankService bankService;
@GetMapping(value="/mybank")
public ModelAndView index(ModelAndView modelandview)
{
	modelandview.setViewName("home");
return modelandview;	
}
@PostMapping(value="mybank/create")
public ResponseEntity<MyBankDB> createUser(@RequestBody MyBankDB db)
{
	bankService.createUser(db);	
return ResponseEntity.ok(db);
}

@PutMapping(value="mybank/{addBal}")
public void addBal(@RequestBody MyBankDB db,@PathVariable("addBal") Integer amount)
{
	bankService.addBalance(db,amount);
}
@DeleteMapping(value="mybank/{withdraw}")
public void withdraw(@RequestBody MyBankDB db,@PathVariable("withdraw") Integer amount)
{
	bankService.withdrawbal(db, amount);
}
}
