package com.fabrick.bank.repository;

import com.fabrick.bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Transaction save(Transaction transaction);
}
