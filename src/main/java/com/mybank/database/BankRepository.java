package com.mybank.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value="bankRepository")
public interface BankRepository extends JpaRepository<MyBankDB,Integer> {

}
