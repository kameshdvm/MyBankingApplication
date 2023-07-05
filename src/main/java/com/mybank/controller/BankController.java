package com.mybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.mybank.database.MyBankDB;
import com.mybank.service.BankService;

@RestController
public class BankController {
	@Autowired
	BankService bankService;

	@GetMapping(value = "/mybank")
	public ModelAndView homePage(ModelAndView view) {
		view.setViewName("home");
		return view;
	}
	
	@PostMapping(value = "/mybank/create")
	public ModelAndView createUser(@ModelAttribute("newUser") MyBankDB db, ModelAndView modelandview ) {
		bankService.createUser(db);
		modelandview.addObject(db);
		modelandview.setViewName("result");
		return modelandview;
	}

	@GetMapping("/mybank/user/{id}")
	public ResponseEntity<MyBankDB> findUser(@RequestParam("id") Integer id) {
		MyBankDB user = bankService.getById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/mybank/balance/{id}")
	public ResponseEntity checkBalance(@RequestParam("id") Integer id) {
		Integer Balance = bankService.checkBalance(id);
		String msg="Your current Balance is: ";
		return ResponseEntity.ok(msg+Balance);
	}

	@GetMapping("/mybank/credit/{id}/{amount}")
	public ResponseEntity credit(@RequestParam("id") Integer id, @RequestParam("amount") Integer amount) {
		bankService.addBalance(id , amount);
		Integer Balance = bankService.checkBalance(id);
		String msg="Transaction Successfull! Your current Balance is: ";
		return ResponseEntity.ok(msg+Balance);
	}

	@GetMapping("/mybank/debit/{id}/{amount}")
	public ResponseEntity debit(@RequestParam("id") Integer id, @RequestParam("amount") Integer amount) {
		Integer Balance = bankService.checkBalance(id);
		if(amount<Balance)
		{
			bankService.withdrawBalance(id, amount);
			Integer currentBalance = bankService.checkBalance(id);
			String msg="Transaction Successfull! Your current Balance is: ";
			return ResponseEntity.ok(msg+currentBalance);
				
		}
		return ResponseEntity.ok("Withdrawal amount exceeded");
	
		/*
		 * @GetMapping(value="/mybank/allusers") public ModelAndView
		 * allUsers(ModelAndView model) { List<MyBankDB> users = bankService.findAll();
		 * ModelAndView addAllAttributes = model.addObject(users); return
		 * addAllAttributes; }
		 */

	}

}
