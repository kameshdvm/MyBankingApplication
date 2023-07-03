package com.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.mybank.database.MyBankDB;
import com.mybank.service.BankService;

@RestController
public class BankController {
	@Autowired
	BankService bankService;

	@GetMapping(value = "/mybank")
	public ModelAndView homePage(ModelAndView modelandview) {
		modelandview.setViewName("home");
		return modelandview;
	}

	@PostMapping(value = "/mybank/create")
	public ModelAndView createUser(@ModelAttribute("newUser") MyBankDB db, ModelAndView modelandview ) {
		bankService.createUser(db);
		modelandview.addObject(db);
		modelandview.setViewName("result");
		return modelandview;
	}

	@GetMapping("/mybank/user/{id}")
	public ResponseEntity<MyBankDB> findUser(@PathVariable("id") Integer id) {
		MyBankDB user = bankService.getById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/mybank/balance/{id}")
	public ResponseEntity<Integer> checkBalance(@PathVariable("id") Integer id) {
		Integer Balance = bankService.checkBalance(id);
		return ResponseEntity.ok(Balance);
	}

	@GetMapping("/mybank/credit/{id}/{amount}")
	public ResponseEntity<Void> credit(@PathVariable("id")int id, @PathVariable("amount") int amount) {
		//Integer newid  = Integer.parseInt(id);
		//Integer newamount  = Integer.parseInt(amount);
		bankService.addBalance(id , amount);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/mybank/{id}/{amount}")
	public ResponseEntity<Void> debit(@PathVariable("id") Integer id, @PathVariable("amount") Integer amount) {
		bankService.withdrawBalance(id, amount);
		return ResponseEntity.ok().build();
	}

}
