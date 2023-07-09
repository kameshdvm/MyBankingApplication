package com.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.mybank.database.MyBankDB;
import com.mybank.service.BankService;

@RestController
public class BankController {
	
	@Autowired
	private BankService bankServices;
	
	@GetMapping(value="/")
	public ModelAndView indexPage(ModelAndView view)
	{
		view.setViewName("index");
		return view;
	}
	@PostMapping(value="/login")
	public ModelAndView login(@ModelAttribute("validate") MyBankDB db,ModelAndView mv)
	{
		Integer accountNum = db.getAccountNum();
		String password = db.getPassword();
	Boolean login = bankServices.login(accountNum,password);	
	if(login)
	{
		MyBankDB user = bankServices.getById(accountNum);
		mv.addObject("userDetails",user);
		mv.setViewName("home");
	}
	else
	{
		
		mv.setViewName("index");
		
	}
	return mv;
	}
	
	@PostMapping(value = "/mybank/create")
	public ModelAndView createUser(@ModelAttribute("newUser") MyBankDB db, ModelAndView modelandview ) {
		bankServices.createUser(db);
		modelandview.addObject(db);
		modelandview.setViewName("result");
		return modelandview;
	}	

	@GetMapping("/mybank/user/{id}")
	public ResponseEntity<MyBankDB> findUser(@RequestParam("id") Integer id) {
		MyBankDB user = bankServices.getById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/mybank/balance/{id}")
	public ResponseEntity<?> checkBalance(@RequestParam("id") Integer id) {
		
		Integer Balance = bankServices.checkBalance(id);
		if(Balance<500)
		{
			throw new NullPointerException("Low Balance "+Balance);
		}
		String msg="Your current Balance is: ";
		return ResponseEntity.ok(msg+Balance);
	}

	@GetMapping("/mybank/credit/{id}/{amount}")
	public ResponseEntity<?> credit(@RequestParam("id") Integer id, @RequestParam("amount") Integer amount) {
		bankServices.addBalance(id , amount);
		Integer Balance = bankServices.checkBalance(id);
		String msg="Transaction Successfull! Your current Balance is: ";
		return ResponseEntity.ok(msg+Balance);
	}

	@GetMapping("/mybank/debit/{id}/{amount}")
	public ResponseEntity<String> debit(@RequestParam("id") Integer id, @RequestParam("amount") Integer amount) {
		Integer Balance = bankServices.checkBalance(id);
		if(amount<Balance)
		{
			bankServices.withdrawBalance(id, amount);
			Integer currentBalance = bankServices.checkBalance(id);
			String msg="Transaction Successfull! Your current Balance is: ";
			return ResponseEntity.ok(msg+currentBalance);
				
		}
		return ResponseEntity.ok("Withdrawal amount exceeded");
	}
		/*
		 * @GetMapping(value="/mybank/allusers") public ModelAndView
		 * allUsers(ModelAndView model) { List<MyBankDB> users = bankService.findAll();
		 * ModelAndView addAllAttributes = model.addObject(users); return
		 * addAllAttributes; }
		 */
	@GetMapping(value="/mybank/transfer")
	public ModelAndView transfer(ModelAndView mv)
	{	
		mv.setViewName("transfer");
		return mv;
	}
	
	@PostMapping(value="/mybank/transaction/{senderAcNum}/{amount}/{SenderPassword}")
	public ResponseEntity<String> transfer(@ModelAttribute("transfer")MyBankDB receiver
			,@RequestParam("senderAcNum")Integer senderAcNum
			,@RequestParam("amount") Integer TransferAmount
			,@RequestParam("SenderPassword")String SenderPassword)
	{
		boolean transfer=true;
		Boolean verifyPassword = bankServices.verifyPassword(senderAcNum, SenderPassword);
		
		if(verifyPassword)
		{
		transfer = bankServices.transfer(receiver, senderAcNum,TransferAmount);
		}
		if(!transfer)
		{ return ResponseEntity.ok("Transaction Successfull!"); }
		else
		{ return ResponseEntity.ok("Transaction Failed!"); }
	}
	
}