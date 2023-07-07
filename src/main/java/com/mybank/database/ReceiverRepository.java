package com.mybank.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository(value="receiverRepository")
public interface ReceiverRepository extends JpaRepository<ReceiverBankDB,Integer> {

}
