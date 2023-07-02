package com.mybank.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<MyBankDB,Integer> {

}
