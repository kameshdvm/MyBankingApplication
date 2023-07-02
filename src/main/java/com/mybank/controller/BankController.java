package com.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

@GetMapping("/mybank/{id}")
public ResponseEntity<MyBankDB> findUser(@PathVariable("id") Integer id)
{
MyBankDB Id = bankService.getById(id);
return ResponseEntity.ok(Id);
}

@PutMapping("/mybank/{id}/{amount}")
public ResponseEntity<Void> credit(@PathVariable("id") Integer id, @PathVariable("amount") Integer amount) {
    bankService.addBalance(id,amount);
    return ResponseEntity.ok().build();
}

@DeleteMapping("/mybank/{id}/{amount}")
public ResponseEntity<Void> debit(@PathVariable("id") Integer id, @PathVariable("amount") Integer amount) {
    bankService.withdrawBalance(id,amount);
    return ResponseEntity.ok().build();
}



}
